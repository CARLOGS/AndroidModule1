package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.NewsEntity

class NewsAdapter (val list: List<NewsEntity>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblNews = view.findViewById<TextView>(R.id.lblNews)
        val imgNews = view.findViewById<ImageView>(R.id.imgNews)

        // Muestra 3 noticias noticias
        fun render(newsEntity: NewsEntity) {
            lblNews.text = newsEntity.news
            when (newsEntity.image) {
                0 -> { imgNews.setImageResource(R.drawable.news1) }
                1 -> { imgNews.setImageResource(R.drawable.news2) }
                2 -> { imgNews.setImageResource(R.drawable.news3) }
            }
            // imgAnimal.setImageResource(id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entity_news, parent, false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.render(list[position])
        // holder.render(list.get(position))
    }
}