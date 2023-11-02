package com.example.movieapptest

import com.example.movieapptest.data.network.retrofit.ServiceInjector
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val appModule = module(override = true) {

    single {
        CompositeDisposable()
    }

}