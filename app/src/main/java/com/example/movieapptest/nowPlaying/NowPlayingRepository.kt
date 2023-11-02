package com.example.movieapptest.nowPlaying

import io.reactivex.Observable

interface NowPlayingRepository {

    fun loadNowPlaying() : Observable<NowPlayingViewState>

}