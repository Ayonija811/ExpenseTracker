package com.appdev.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.srivastava.expensetracker.databinding.ActivityExpenseBinding
import kotlinx.coroutines.launch

class ExpenseActivity : AppCompatActivity() {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var binding : ActivityExpenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // with viewmodel
        viewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        var userExpenditure = UserExpenditure()

        binding.btnSubmit.setOnClickListener {
            //retrieve user input
            val entertainmentValue = binding.edEntertainment.text.toString()
            val foodValue = binding.edFood.text.toString()
            val rentValue = binding.edRent.text.toString()
            val travelValue = binding.edTravel.text.toString()
            val miscellaneousValue = binding.edMiscellaneous.text.toString()

            userExpenditure.feedUserInput(entertainmentValue,
                foodValue,
                rentValue,
                travelValue,
                miscellaneousValue
            )

            lifecycleScope.launch {
                viewModel.UpdateExpense(userExpenditure)
            }
            // Clear input fields
            binding.edEntertainment.text.clear()
            binding.edFood.text.clear()
            binding.edRent.text.clear()
            binding.edTravel.text.clear()
            binding.edMiscellaneous.text.clear()
        }

        viewModel.expenseValue.observe(this, Observer { totalExpense ->
            binding.tvTotalExpense.text = "Total Expense : $totalExpense"
        })

    }
}