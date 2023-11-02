package com.example.movieapptest.popular

import com.example.movieapptest.data.model.Movie

sealed class PopularViewState {

    class NoInternetConnection(val message : String) : PopularViewState()

    object Loading : PopularViewState()
    class SuccessPopularListState(val data : List<Movie>) : PopularViewState()
    class FailPopularListState(val message : String) : PopularViewState()
}