package com.example.movieapptest.movieDetail

import io.reactivex.Observable

interface MovieDetailRepository {

    fun loadMovieDetail(movieId : String) : Observable<MovieDetailViewState>

}