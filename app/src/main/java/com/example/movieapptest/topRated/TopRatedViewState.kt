package com.example.movieapptest.topRated

import com.example.movieapptest.data.model.Movie

sealed class TopRatedViewState {

    class NoInternetConnection(val message : String) : TopRatedViewState()

    object Loading : TopRatedViewState()
    class SuccessTopRatedListState(val data : List<Movie>) : TopRatedViewState()
    class FailTopRatedListState(val message : String) : TopRatedViewState()
}