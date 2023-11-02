package com.example.movieapptest.nowPlaying

import com.example.movieapptest.data.network.services.MovieService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NowPlayingRepositoryImpl(private val service : MovieService) : NowPlayingRepository {

    override fun loadNowPlaying(): Observable<NowPlayingViewState> = service.loadNowPlaying()
        .subscribeOn(Schedulers.io())
        .map<NowPlayingViewState> { NowPlayingViewState.SuccessNowPlayingListState(it.results) }
        .startWith( NowPlayingViewState.Loading )
        .onErrorReturn { NowPlayingViewState.FailNowPlayingListState(it.localizedMessage ?: "Unknown Error.") }
}