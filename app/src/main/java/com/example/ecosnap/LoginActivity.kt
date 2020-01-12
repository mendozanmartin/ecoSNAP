package com.example.ecosnap

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        userName = findViewById(R.id.username)
        password = findViewById(R.id.password)
        signInButton = findViewById(R.id.login)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser


        signInButton.setOnClickListener {
            var emailInput = username.text.toString()
            var passwordInput = password.text.toString()
            if (emailInput != "" && passwordInput != "" ) {
                auth.signInWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Message", "Authentication Successful")
                            val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Message", "Authentication Failed")
                        }
                    }
            }

        }
    }





}
