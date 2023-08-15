package com.example.exercisesgym


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(var news: List<Cell>, var context: Context) :
    RecyclerView.Adapter<NewsAdapter.MyAdapter>() {

    class MyAdapter(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.newsImage)
        val title: TextView = view.findViewById(R.id.newsTitle)
        val description: TextView = view.findViewById(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_exercise_cell, parent, false)
        return MyAdapter(view)
    }

    override fun getItemCount(): Int {
        return news.count()
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.title.text = news[position].title
        holder.description.text = news[position].desc

        val imageId = context.resources.getIdentifier(
            news[position].image,
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)
    }
}