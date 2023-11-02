package com.example.movieapptest

import android.app.Application
import com.example.movieapptest.nowPlaying.nowPlayingModule
import com.example.movieapptest.popular.popularModule
import com.example.movieapptest.topRated.topRatedModule
import com.example.movieapptest.upcoming.upcomingModule
import movieDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidFileProperties()
            modules(listOf(appModule, nowPlayingModule, popularModule, topRatedModule, upcomingModule, movieDetailModule))
        }

    }

}