package com.example.movieapptest.popular

import com.example.movieapptest.data.network.retrofit.ServiceInjector
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularModule = module {

    single {
        PopularRepositoryImpl(get())
    }

    viewModel {
        PopularViewModel(get(), get())
    }

}