package com.example.movieapptest.utils

import io.reactivex.Scheduler

/**
 *  Interface to mock different threads during testing.
 * */
interface Scheduler {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}