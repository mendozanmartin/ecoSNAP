package com.example.ecosnap

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

import kotlinx.android.synthetic.main.activity_leaderboard.*

class Leaderboard : AppCompatActivity() {
    private lateinit var listView: ListView
    private var usersList:ArrayList<HashMap<String,String>> = ArrayList()

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        setSupportActionBar(toolbar)
        listView = findViewById<ListView>(R.id.leaderboard_list_view)


        usersCollection.orderBy("points", Query.Direction.DESCENDING).get().addOnSuccessListener {result->
            for (document in result) {
                Log.d("MESSAGE", "${document.id} => ${document.data}")
                usersList.add(document.data as HashMap<String,String>)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usersList)
            listView.adapter = adapter
        }.addOnFailureListener{exception->
            Log.d("ERROR", "Error getting documents: ", exception)
        }
    }


}
