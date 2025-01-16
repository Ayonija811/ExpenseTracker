package com.srivastava.expensetracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* This Singleton class ensures same expenseDB object is used throught app lifecycle.
* */
@Database(entities = [Expenses::class], version = 1, exportSchema = false)
abstract class TotalExpenseDB : RoomDatabase() {

    abstract fun totalExpenseDao(): TotalExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: TotalExpenseDB? = null

        fun getDatabase(context: Context): TotalExpenseDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TotalExpenseDB::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
