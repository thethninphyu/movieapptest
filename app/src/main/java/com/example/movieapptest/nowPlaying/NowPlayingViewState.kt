package com.example.movieapptest.nowPlaying

import com.example.movieapptest.data.model.Movie

sealed class NowPlayingViewState {

    class NoInternetConnection(val message: String) : NowPlayingViewState()

    object Loading : NowPlayingViewState()
    class SuccessNowPlayingListState(val data : List<Movie>) : NowPlayingViewState()
    class FailNowPlayingListState(val message : String) : NowPlayingViewState()

}