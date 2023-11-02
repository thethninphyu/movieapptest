package com.example.movieapptest.topRated

import io.reactivex.Observable

interface TopRatedRepository {

    fun loadTopRated() : Observable<TopRatedViewState>
}