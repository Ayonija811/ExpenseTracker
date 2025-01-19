package com.appdev.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.srivastava.expensetracker.R


class MainActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth
    private lateinit var btnCheckAuth : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        //auth =  FirebaseAuth.getInstance();
        btnCheckAuth = findViewById(R.id.btnCheckAuth)

        btnCheckAuth.setOnClickListener{
            startActivity(Intent(this,ExpenseActivity::class.java))
           /* val currentUser = auth.currentUser
            if(currentUser != null){
              startActivity(Intent(this,ExpenseActivity::class.java))
            }
            else{
                startActivity(Intent(this,SignInActivity::class.java))
            }*/
        }



     /*   //initialize firebase databse system
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        //write the value to the "message' node
        myRef.setValue("Hello, world")

        // Add listener to read the value od "message"
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val value = snapshot.getValue(String :: class.java) //Read the value
               Log.d("Firebase", "Value is $value")
            }
            override fun onCancelled(error: DatabaseError) {
               Log.d("Firebase","Failed to read value",error.toException())
            }
        })*/
    }
}