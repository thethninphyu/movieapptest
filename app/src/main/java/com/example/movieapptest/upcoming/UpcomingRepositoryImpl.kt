package com.example.movieapptest.upcoming

import com.example.movieapptest.data.network.services.MovieService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class UpcomingRepositoryImpl(private val service: MovieService) : UpcomingRepository {

    override fun loadUpcoming(): Observable<UpcomingViewState> = service.loadUpcoming()
        .subscribeOn(Schedulers.io())
        .map<UpcomingViewState> { UpcomingViewState.SuccessUpcomingListState(it.results) }
        .startWith(UpcomingViewState.Loading)
        .onErrorReturn { UpcomingViewState.FailUpcomingListState(it.localizedMessage ?: "Unknown Error.") }
}