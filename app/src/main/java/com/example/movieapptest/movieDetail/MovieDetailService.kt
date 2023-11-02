package com.example.movieapptest.movieDetail

import com.example.movieapptest.API_KEY
import com.example.movieapptest.data.network.responses.MovieDetailResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("movie/{movie_id}")
    fun loadMovieDetail(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKeky : String = API_KEY
    ) : Observable<MovieDetailResponse>
}