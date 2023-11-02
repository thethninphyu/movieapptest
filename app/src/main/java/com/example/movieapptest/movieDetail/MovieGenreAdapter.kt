package com.example.movieapptest.movieDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapptest.R
import com.example.movieapptest.data.model.Genre
import kotlinx.android.synthetic.main.gener_rv_item.view.*

class MovieGenreAdapter : RecyclerView.Adapter<MovieGenreAdapter.GenreViewHolder>(){

    private var list : List<Genre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gener_rv_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun setGenreList(list : List<Genre>){
        this.list = list
        notifyDataSetChanged()
    }

    class GenreViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindData(data : Genre){
            itemView.tvGenre.text = data.name
        }
    }
}