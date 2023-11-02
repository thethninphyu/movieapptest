package com.example.movieapptest.topRated

import com.example.movieapptest.data.network.retrofit.ServiceInjector
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topRatedModule = module {

    single {
        TopRatedRepositoryImpl(get())
    }

    viewModel {
        TopRatedViewModel(get(), get())
    }

}