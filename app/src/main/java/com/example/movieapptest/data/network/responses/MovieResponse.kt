package com.example.movieapptest.data.network.responses


import com.example.movieapptest.data.model.Dates
import com.example.movieapptest.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)