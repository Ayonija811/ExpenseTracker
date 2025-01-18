package com.srivastava.expensetracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    //Define DB object
    private val expenseDao : TotalExpenseDao = TotalExpenseDB.getDatabase(application).totalExpenseDao()
    private val expenseFireDb = FirebaseDatabase.getInstance()
    private var isRoomDBEmpty : Boolean = true

    private val _totalExpense = MutableLiveData<Double>()
    val expenseValue : LiveData<Double> get() = _totalExpense

    init{
        //Create a check to ensure localDb and FirebaseDB data is consistent.
        isDatabaseEmpty{ isRowCountEmptyCheck ->
            if(isRowCountEmptyCheck){
                isRoomDBEmpty = true
            }else{
                isRoomDBEmpty = false
                fetchTotalExpense()
            }
        }
    }

    private fun isDatabaseEmpty(callback: (Boolean)-> Unit){
        viewModelScope.launch(Dispatchers.IO){
            callback(expenseDao.getRowCount() == 0)
        }
    }
    private fun fetchTotalExpense() {
       viewModelScope.launch(Dispatchers.IO){

           val totalExpenseRoom = expenseDao.getTotalExpense()
           val totalExpenseFire = expenseFireDb.getReference("TotalExpense")

           totalExpenseFire.addValueEventListener(object : ValueEventListener{
               override fun onDataChange(snapshot: DataSnapshot) {
                  val totalExpenseFireBase = snapshot.getValue(Double :: class.java)
                   if(totalExpenseFireBase == totalExpenseRoom){
                       _totalExpense.postValue(totalExpenseRoom?: 0.0)
                   }
               }
               override fun onCancelled(error: DatabaseError) {
               }
           })
       }
    }

    //recommended to remove viewmodelscope as you are using suspend function
     suspend fun UpdateExpense(userExpenditure: UserExpenditure){
        viewModelScope.launch(Dispatchers.IO) {
            var currentExpense = userExpenditure.entertainment + userExpenditure.food + userExpenditure.rent + userExpenditure.travel + userExpenditure.miscellaneous
            if(isRoomDBEmpty == true){
                val primaryKey = 1
                userExpenditure.updateTotalExpense(0.0,currentExpense)
                val expenses = Expenses(primaryKey,userExpenditure.getTotalExpenditure())
                expenseDao.insertTotalExpense(expenses)
                isRoomDBEmpty = false
            }else{
                userExpenditure.updateTotalExpense(expenseDao.getTotalExpense(),currentExpense)
                expenseDao.updateTotalExpenses(userExpenditure.getTotalExpenditure())
            }
            //update all DBs
            expenseFireDb.getReference("TotalExpense").setValue(userExpenditure.getTotalExpenditure())
            expenseFireDb.getReference("Entertainment").setValue(userExpenditure.entertainment)
            expenseFireDb.getReference("Food").setValue(userExpenditure.food)
            expenseFireDb.getReference("Rent").setValue(userExpenditure.rent)
            expenseFireDb.getReference("Travel").setValue(userExpenditure.travel)
            expenseFireDb.getReference("MiscellaneousExpense").setValue(userExpenditure.miscellaneous)

            _totalExpense.postValue(userExpenditure.getTotalExpenditure())

        }
    }
}
