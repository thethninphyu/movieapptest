package com.example.movieapptest.movieDetail

import com.example.movieapptest.data.network.responses.MovieDetailResponse

sealed class MovieDetailViewState {

    object LoadingMovieDetailState : MovieDetailViewState()
    data class SuccessMovieDetailState(val data: MovieDetailResponse) : MovieDetailViewState()
    data class FailMovieDetailState(val msg: String) : MovieDetailViewState()

}