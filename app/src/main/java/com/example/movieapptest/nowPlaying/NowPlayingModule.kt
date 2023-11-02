package com.example.movieapptest.nowPlaying

import com.example.movieapptest.data.network.retrofit.ServiceInjector
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nowPlayingModule = module {

    single {
        ServiceInjector.getMovieService()
    }

    single {
        NowPlayingRepositoryImpl(get())
    }

    viewModel {
        NowPlayingViewModel(get(), get())
    }

}