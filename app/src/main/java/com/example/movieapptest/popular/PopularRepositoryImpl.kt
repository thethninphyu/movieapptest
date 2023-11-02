package com.example.movieapptest.popular

import com.example.movieapptest.data.network.services.MovieService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PopularRepositoryImpl(private val service : MovieService) : PopularRepository {

    override fun loadPopular(): Observable<PopularViewState> = service.loadPopular()
        .subscribeOn(Schedulers.io())
        .map<PopularViewState> {PopularViewState.SuccessPopularListState(it.results)}
        .startWith(PopularViewState.Loading)
        .onErrorReturn { PopularViewState.FailPopularListState(it.localizedMessage ?: "Unknown Error.") }
}