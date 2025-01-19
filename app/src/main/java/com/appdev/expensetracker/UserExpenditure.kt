package com.appdev.expensetracker

class UserExpenditure{
    var entertainment : Double
    var food : Double
    var rent : Double
    var travel : Double
    var miscellaneous : Double
    var totalExpense : Double

    constructor(){
        this.entertainment = 0.0
        this.food = 0.0
        this.rent = 0.0
        this.travel = 0.0
        this.miscellaneous = 0.0
        this.totalExpense = 0.0
    }

    constructor(entertainment : Double, food : Double, rent : Double, travel : Double, miscellaneous : Double, totalExpense : Double){
        this.entertainment = entertainment
        this.food = food
        this.rent = rent
        this.travel = travel
        this.miscellaneous = miscellaneous
        this.totalExpense = entertainment + food + rent + travel + miscellaneous
    }

    fun getTotalExpenditure() : Double{
        return this.totalExpense
    }

    fun feedUserInput(entertainment: String, food: String, rent: String, travel: String, miscellaneous: String){
        if(entertainment.isNullOrEmpty()){
            this.entertainment = 0.0
        }
        else{
            this.entertainment = entertainment.toDouble()
        }
        if(food.isNullOrEmpty()){
            this.food = 0.0
        }
        else{
            this.food = food.toDouble()
        }
        if(rent.isNullOrEmpty()){
            this.rent = 0.0
        }
        else{
            this.rent = rent.toDouble()
        }
        if(travel.isNullOrEmpty()){
            this.travel = 0.0
        }
        else{
            this.travel = travel.toDouble()
        }
        if(miscellaneous.isNullOrEmpty()){
            this.miscellaneous = 0.0
        }
        else{
            this.miscellaneous = miscellaneous.toDouble()
        }
    }

    fun updateTotalExpense(prevTotalExpense : Double, newExpense : Double){
        //for production use this "requireNotNull(value) { "Value must not be null" } in place of assert(){}"
        assert(prevTotalExpense != null){"prevTotalExpense must not be null"}
        this.totalExpense = prevTotalExpense + newExpense

    }
}

