package com.example.movieapptest.topRated

import com.example.movieapptest.data.network.services.MovieService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class TopRatedRepositoryImpl(private val service: MovieService) : TopRatedRepository {

    override fun loadTopRated(): Observable<TopRatedViewState> = service.loadTopRated()
        .subscribeOn(Schedulers.io())
        .map<TopRatedViewState> { TopRatedViewState.SuccessTopRatedListState(it.results) }
        .startWith(TopRatedViewState.Loading)
        .onErrorReturn { TopRatedViewState.FailTopRatedListState(it.localizedMessage ?: "Unknown Error.") }
}