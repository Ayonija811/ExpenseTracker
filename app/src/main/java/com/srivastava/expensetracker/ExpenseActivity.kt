package com.srivastava.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        //initialize firebase databse system
        val database = FirebaseDatabase.getInstance()
        val entertainmentRef = database.getReference("Entertainment")
        val foodRef = database.getReference("Food")
        val rentRef = database.getReference("Rent")
        val travelRef = database.getReference("Travel")
        val miscellaneousRef = database.getReference("MiscellaneousExpense")
        val totalExpenseRef = database.getReference("TotalExpense")


        //find ids
        val entertainmentEditText = findViewById<EditText>(R.id.ed_entertainment)
        val foodEditText = findViewById<EditText>(R.id.ed_food)
        val rentEditText = findViewById<EditText>(R.id.ed_rent)
        val travelEditText = findViewById<EditText>(R.id.ed_travel)
        val miscellaneousEditText = findViewById<EditText>(R.id.ed_miscellaneous)
        val submitButton = findViewById<Button>(R.id.btn_submit)


        submitButton.setOnClickListener{
            //retrieve user input
            val entertainmentValue = entertainmentEditText.text.toString()
            val foodValue = foodEditText.text.toString()
            val rentValue = rentEditText.text.toString()
            val travelValue = travelEditText.text.toString()
            val miscellaneousValue = miscellaneousEditText.text.toString()

            // set value to firebase
            entertainmentRef.setValue(entertainmentValue)
            foodRef.setValue(foodValue)
            rentRef.setValue(rentValue)
            travelRef.setValue(travelValue)
            miscellaneousRef.setValue(miscellaneousValue)

            // calculate total
            val totalExpense = entertainmentValue.toDouble() + foodValue.toDouble() + rentValue.toDouble() + travelValue.toDouble() + miscellaneousValue.toDouble()
            totalExpenseRef.setValue(totalExpense)

            // Clear input fields
            entertainmentEditText.text.clear()
            foodEditText.text.clear()
            rentEditText.text.clear()
            travelEditText.text.clear()
            miscellaneousEditText.text.clear()

        }
    }
}