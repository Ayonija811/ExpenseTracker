package com.srivastava.expensetracker

import android.app.Application
import android.widget.Toast
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

    private val _totalExpense = MutableLiveData<Double>()
    val expenseValue : LiveData<Double> get() = _totalExpense

    init{
        //Create a check to ensure localDb and FirebaseDB data is consistent.
        fetchTotalExpense()
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

         /*  val expense = Expenses(totalExpense = totalExpenseRoom ?: 0.0)
           expenseDao.insertTotalExpense(expense)*/
       }
    }

    //recommended to remove viewmodelscope as you are using suspend function
     suspend fun UpdateExpense(userExpenditure: UserExpenditure){
        viewModelScope.launch(Dispatchers.IO) {
            expenseFireDb.getReference("Entertainment").setValue(userExpenditure.entertainment)
            expenseFireDb.getReference("Food").setValue(userExpenditure.food)
            expenseFireDb.getReference("Rent").setValue(userExpenditure.rent)
            expenseFireDb.getReference("Travel").setValue(userExpenditure.travel)
            expenseFireDb.getReference("MiscellaneousExpense").setValue(userExpenditure.miscellaneous)

            var currentExpense = userExpenditure.entertainment + userExpenditure.food + userExpenditure.rent + userExpenditure.travel + userExpenditure.miscellaneous
            userExpenditure.calcTotalExpense(expenseDao.getTotalExpense(),currentExpense)
            expenseFireDb.getReference("TotalExpense").setValue(userExpenditure.getTotalExpenditure())
            //updated in roomDB
            expenseDao.updateTotalExpenses(userExpenditure.getTotalExpenditure())
            _totalExpense.postValue(userExpenditure.getTotalExpenditure())

        }
    }
    /*   private fun showToast(message : String){
         viewModelScope.launch(Dispatchers.Main){
             Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show()
         }
     }
 */

}
