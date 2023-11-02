package com.example.movieapptest.nowPlaying

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapptest.IMAGE_URL
import com.example.movieapptest.R
import com.example.movieapptest.data.model.Movie
import com.example.movieapptest.movieDetail.MovieDetailActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    private var list: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder =
        NowPlayingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun setNewMovieList(data: List<Movie>) {
        this.list = data
        notifyDataSetChanged()
    }

    class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: Movie) {
            itemView.tvMovieTitle.text = data.title

            Glide.with(itemView.context).load(IMAGE_URL + data.posterPath)
                .apply(RequestOptions().placeholder(R.drawable.movieholder).error(R.drawable.movieholder))
                .into(itemView.ivMoviePoster)

            itemView.setOnClickListener {
                var intent = Intent(itemView.context, MovieDetailActivity :: class.java)
                intent.putExtra("movieId", "${data.id}")
                itemView.context.startActivity(intent)
            }
        }

    }
}