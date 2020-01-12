package com.example.ecosnap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.ecosnap.models.User

class LeaderboardAdapter(private val context: Context,
                         private val dataSource: ArrayList<User>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_leaderboard, parent, false)
//// Get title element
//        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView
//
//// Get subtitle element
//        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView
//
//// Get detail element
//        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView
//
//// Get thumbnail element
//        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView
        return rowView
    }
}