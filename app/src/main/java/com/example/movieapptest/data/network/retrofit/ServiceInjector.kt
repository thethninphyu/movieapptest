package com.example.movieapptest.data.network.retrofit

import com.example.movieapptest.BASE_URL
import com.example.movieapptest.BuildConfig
import com.example.movieapptest.data.network.services.MovieService
import com.example.movieapptest.movieDetail.MovieDetailService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceInjector {

    private fun provideRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.networkInterceptors().add(httpLoggingInterceptor)

        val okHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getMovieService(): MovieService {
        return provideRetrofit().create(MovieService::class.java)
    }

    fun getMovieDetailService() : MovieDetailService = provideRetrofit().create(MovieDetailService::class.java)

}