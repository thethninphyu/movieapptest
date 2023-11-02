package com.example.movieapptest.movieDetail

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieDetailRepositoryImpl(private val service: MovieDetailService) : MovieDetailRepository {
    override fun loadMovieDetail(movieId : String): Observable<MovieDetailViewState> = service.loadMovieDetail(movieId)
        .subscribeOn(Schedulers.io())
        .map <MovieDetailViewState> { MovieDetailViewState.SuccessMovieDetailState(it)}
        .startWith(MovieDetailViewState.LoadingMovieDetailState)
        .onErrorReturn {
            MovieDetailViewState.FailMovieDetailState(it.localizedMessage ?: "Unknown Error.")
        }

}