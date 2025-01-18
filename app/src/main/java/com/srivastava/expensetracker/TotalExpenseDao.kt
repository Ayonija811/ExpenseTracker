package com.srivastava.expensetracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TotalExpenseDao {

    @Insert
    suspend fun insertTotalExpense(expenses: Expenses)

    @Query("SELECT totalExpense FROM expenses")
    suspend fun getTotalExpense() : Double

    @Query("UPDATE expenses SET totalExpense = :newAmount ")
    suspend fun updateTotalExpenses(newAmount : Double)

    @Query("SELECT COUNT(*) FROM expenses")
    suspend fun getRowCount() : Int

}