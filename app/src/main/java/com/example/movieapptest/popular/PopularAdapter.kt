package com.example.movieapptest.popular

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapptest.IMAGE_URL
import com.example.movieapptest.R
import com.example.movieapptest.data.model.Movie
import com.example.movieapptest.movieDetail.MovieDetailActivity
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var list : List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder =
        PopularViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun setNewMovieList(list: List<Movie>){
        this.list = list
        notifyDataSetChanged()
    }

    class PopularViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindData(movie: Movie){
            itemView.tvMovieTitle.text = movie.title

            Glide.with(itemView.context).load(IMAGE_URL + movie.posterPath)
                .apply(RequestOptions().placeholder(R.drawable.movieholder).error(R.drawable.movieholder))
                .into(itemView.ivMoviePoster)

            itemView.setOnClickListener {
                var intent = Intent(itemView.context, MovieDetailActivity :: class.java)
                intent.putExtra("movieId", "${movie.id}")
                intent.putExtra("movieTitle", "${movie.title}")
                itemView.context.startActivity(intent)
            }
        }
    }

}