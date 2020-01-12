package com.example.ecosnap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userName = findViewById(R.id.username)
        password = findViewById(R.id.password)
        signInButton = findViewById(R.id.login)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
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
                            var df = Leaderboard()
                            Log.d("blablabla", df.toString())
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Message", "Authentication Failed")
                        }
                    }
            }

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

}
fun Leaderboard() : ArrayList<HashMap<String,  String>>{
    lateinit var listView: ListView
    var usersList:ArrayList<HashMap<String,String>> = ArrayList()

    val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("Users")
    usersCollection.orderBy("points", Query.Direction.DESCENDING).get().addOnSuccessListener { result ->
        for (document in result) {
            Log.d("MESSAGE", "${document.id} => ${document.data}")

            usersList.add(document.data as HashMap<String, String>)
        }

    }
    return usersList

}