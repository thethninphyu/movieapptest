package com.example.movieapptest.upcoming

import com.example.movieapptest.data.model.Movie

sealed class UpcomingViewState {

    class NoInternetConnection(val message : String) : UpcomingViewState()

    object Loading : UpcomingViewState()
    class SuccessUpcomingListState(val data : List<Movie>) : UpcomingViewState()
    class FailUpcomingListState(val message : String) : UpcomingViewState()
}