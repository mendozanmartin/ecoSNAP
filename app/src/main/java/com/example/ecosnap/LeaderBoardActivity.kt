package com.example.ecosnap

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.leaderboard.*

class LeaderBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard)
        leaderBoard_outline.apply {
            layoutManager = LinearLayoutManager( this@LeaderBoardActivity)
            adapter = LeaderBoardAdapter()
        }
    }
}