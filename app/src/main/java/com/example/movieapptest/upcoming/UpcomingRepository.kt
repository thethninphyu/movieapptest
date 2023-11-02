package com.example.movieapptest.upcoming

import io.reactivex.Observable

interface UpcomingRepository {

    fun loadUpcoming() : Observable<UpcomingViewState>
}