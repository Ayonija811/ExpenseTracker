package com.srivastava.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.srivastava.expensetracker.databinding.ActivityExpenseBinding

class ExpenseActivity : AppCompatActivity() {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var binding : ActivityExpenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*//initialize firebase databse system
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
        }*/

        // with viewmodel
        viewModel = ViewModelProvider(this).get(ExpenseViewModel ::class.java)

        /*val entertainmentEditText = findViewById<EditText>(R.id.ed_entertainment)
        val foodEditText = findViewById<EditText>(R.id.ed_food)
        val rentEditText = findViewById<EditText>(R.id.ed_rent)
        val travelEditText = findViewById<EditText>(R.id.ed_travel)
        val miscellaneousEditText = findViewById<EditText>(R.id.ed_miscellaneous)
        val submitButton = findViewById<Button>(R.id.btn_submit)
*/
        viewModel.expenseValue.observe(this, Observer { totalExpense ->
            findViewById<TextView>(R.id.tv_totalExpense).text = "Total Expense : $totalExpense"
        })


        binding.btnSubmit.setOnClickListener{
            //retrieve user input
            val entertainmentValue = binding.edEntertainment.text.toString()
            val foodValue = binding.edFood.text.toString()
            val rentValue = binding.edRent.text.toString()
            val travelValue = binding.edTravel.text.toString()
            val miscellaneousValue = binding.edMiscellaneous.text.toString()
            //val totalExpenseValue = totalExpenseTextView.text.toString()

            viewModel.serverDbUpdate(entertainmentValue,foodValue,rentValue,travelValue,miscellaneousValue)

            // Clear input fields
            binding.edEntertainment.text.clear()
            binding.edFood.text.clear()
            binding.edRent.text.clear()
            binding.edTravel.text.clear()
            binding.edMiscellaneous.text.clear()
        }

    }
}