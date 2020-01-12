package com.example.ecosnap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardAdapter : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>(){
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_layout, parent, false)
        return ViewHolder(layoutView)
    }

    override fun getItemCount() = 50

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
