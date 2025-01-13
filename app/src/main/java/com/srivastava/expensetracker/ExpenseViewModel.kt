package com.srivastava.expensetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class ExpenseViewModel : ViewModel() {
    val expenseDb = FirebaseDatabase.getInstance()
    //create mutable variables
    private val _totalExpense = MutableLiveData<String>()
    //set ui data using livedata
    val expenseValue : LiveData<String> get() = _totalExpense
    //create update function to update mutable variables
    fun updateExpense(totalExpense : String){
        _totalExpense.value = expenseDb.getReference("TotalExpense").toString()
    }

    // set value to firebase
    fun serverDbUpdate(entertainmentValue : String, foodValue : String, rentValue : String, travelValue : String, miscellaneousValue : String){
        expenseDb.getReference("Entertainment").setValue(entertainmentValue)
        expenseDb.getReference("Food").setValue(foodValue)
        expenseDb.getReference("Rent").setValue(rentValue)
        expenseDb.getReference("Travel").setValue(travelValue)
        expenseDb.getReference("MiscellaneousExpense").setValue(miscellaneousValue)

        val totalExpense = entertainmentValue.toDouble() + foodValue.toDouble() + rentValue.toDouble() + travelValue.toDouble() + miscellaneousValue.toDouble()
        _totalExpense.value = totalExpense.toString()
    }
}
