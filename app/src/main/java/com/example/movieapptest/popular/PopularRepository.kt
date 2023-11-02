package com.example.movieapptest.popular

import io.reactivex.Observable

interface PopularRepository {

    fun loadPopular() : Observable<PopularViewState>

}