package com.example.movieapptest.upcoming

import com.example.movieapptest.data.network.retrofit.ServiceInjector
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val upcomingModule = module {

    single {
        UpcomingRepositoryImpl(get())
    }

    viewModel {
        UpcomingViewModel(get(), get())
    }

}