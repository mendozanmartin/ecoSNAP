package com.example.ecosnap

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.leaderboard.*

class LeaderBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.leaderboard)
        LeaderBoard_outline.apply {
            var layoutManager = LinearLayoutManager( this@LeaderBoardActivity)
            var adapter = LeaderBoardAdapter()
        }
    }
}