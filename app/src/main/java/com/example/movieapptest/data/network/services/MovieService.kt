package com.example.movieapptest.data.network.services

import com.example.movieapptest.API_KEY
import com.example.movieapptest.data.network.responses.MovieDetailResponse
import com.example.movieapptest.data.network.responses.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    fun loadNowPlaying(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page: Int = 1
    ) : Observable<MovieResponse>

    @GET("movie/popular")
    fun loadPopular(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page: Int = 1
    ) : Observable<MovieResponse>

    @GET("movie/top_rated")
    fun loadTopRated(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page: Int = 1
    ) : Observable<MovieResponse>

    @GET("movie/upcoming")
    fun loadUpcoming(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ) : Observable<MovieResponse>

}