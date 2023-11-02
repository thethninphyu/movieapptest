package com.example.movieapptest.movieDetail

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.bumptech.glide.request.RequestOptions
import com.example.movieapptest.IMAGE_URL
import com.example.movieapptest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_moviedetail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    val movieDetailViewModel: MovieDetailViewModel by viewModel()

    lateinit var genreAdapter: MovieGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetail)

        val movieId = intent.getStringExtra("movieId")
        val toolbar = supportActionBar
        toolbar!!.title = "Detail"
        toolbar!!.setDisplayHomeAsUpEnabled(true)

        genreAdapter = MovieGenreAdapter()
        rvgenre.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
        rvgenre.adapter = genreAdapter

        movieDetailViewModel.loadMovieDetail(movieId)
        movieDetailViewModel.viewState.observe(this, Observer {
            render(it)
        })

    }

    private fun render(state: MovieDetailViewState) {
        when (state) {
            is MovieDetailViewState.LoadingMovieDetailState -> renderMovieDetailLoadingState()
            is MovieDetailViewState.SuccessMovieDetailState -> renderMovieDetailSuccessState(state)
            is MovieDetailViewState.FailMovieDetailState -> renderMovieDetailFailState(state)
        }
    }

    private fun renderMovieDetailLoadingState() {
        Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT).show()
    }

    private fun renderMovieDetailSuccessState(state: MovieDetailViewState.SuccessMovieDetailState) {

        Glide.with(applicationContext).load(IMAGE_URL + state.data.posterPath)
            .apply(RequestOptions().placeholder(R.drawable.movieholder).error(R.drawable.movieholder))
            .into(imgviewDetailPoster)

        tvDescription.text = state.data.overview
        tvMovieTitle.text = state.data.title
        var runtime = state.data.runtime
        tvRuntime.text = "Duration : ${runtime / 60} hours ${runtime%60} minutes"
        tvReleaseDate.text = "Release Date : ${state.data.releaseDate}"
        genreAdapter.setGenreList(state.data.genres)

    }

    private fun renderMovieDetailFailState(state: MovieDetailViewState.FailMovieDetailState) {
        Toast.makeText(applicationContext, state.msg, Toast.LENGTH_LONG).show()
    }

}