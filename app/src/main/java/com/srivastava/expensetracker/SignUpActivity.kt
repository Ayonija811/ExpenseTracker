/*
package com.srivastava.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnSignUp : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
//DELETE_ME: Dummy test comment
        auth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener{
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                signUp(email,password)
            }
            else{
                Toast.makeText(this,"Please fill in all fields",Toast.LENGTH_SHORT).show()
            }

        }


    }
    private fun signUp(email : String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    startActivity(Intent(this,ExpenseActivity ::class.java))
                }
                else{
                    Toast.makeText(this,"Sign-Up failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}*/
