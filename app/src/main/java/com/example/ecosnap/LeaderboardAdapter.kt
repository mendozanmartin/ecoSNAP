package com.example.ecosnap

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.leaderboard_layout.view.*
import java.util.Arrays

class LeaderBoardAdapter : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_layout, parent, false)
        return ViewHolder(layoutView)
    }

    override fun getItemCount() = 50
    private var count = 1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lateinit var listView: ListView
        var usersList:ArrayList<HashMap<String,String>> = ArrayList()


        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("Users")

        usersCollection.orderBy("points", Query.Direction.DESCENDING).get().addOnSuccessListener { result ->
            if (count > result.size()){
                holder.itemView.user_names.text = "empty"
                holder.itemView.Points.text= "empty"
            } else {
                var i = 0
                for (document in result) {
                    i++
                    if (i > count) break
                    Log.d("MESSAGE", "${document.id} => ${document.data}")
                    var a = document.data.get("firstName")

                    holder.itemView.user_names.text = a.toString()
                    var b = document.data.get("points")
                    b.toString()
                    holder.itemView.Points.text = b.toString()
                    usersList.add(document.data as HashMap<String, String>)


                }
            }
            count++

        }

    }
    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view)


}
