package com.srivastava.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnCheckAuth : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        auth =  FirebaseAuth.getInstance();
        btnCheckAuth = findViewById(R.id.btnCheckAuth)

        btnCheckAuth.setOnClickListener{
            val currentUser = auth.currentUser
            if(currentUser != null){
              startActivity(Intent(this,ExpenseActivity::class.java))
            }
            else{
                startActivity(Intent(this,SignInActivity::class.java))
            }
        }





    }
}