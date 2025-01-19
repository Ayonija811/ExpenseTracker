/*

package com.srivastava.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etpassword : EditText
    private lateinit var etEmail : EditText
    private lateinit var btnSignIn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etEmail)
        etpassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)

        btnSignIn.setOnClickListener{
            val email = etEmail.text.toString().trim()
            val password = etpassword.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                signIn(email,password);
            }
            else{
                Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signIn(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    startActivity(Intent(this,ExpenseActivity :: class.java))
                }
                else{
                    Toast.makeText(this,"Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
*/
