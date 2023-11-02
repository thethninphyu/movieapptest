package com.example.movieapptest.nowPlaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptest.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NowPlayingViewModel(
    private val repositoryImpl: NowPlayingRepositoryImpl,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    var viewState : MutableLiveData<NowPlayingViewState> = MutableLiveData()

    fun loadNowPlaying() {
        repositoryImpl.loadNowPlaying()
            .observeOn(Schedulers.io())
            .subscribe { viewState.postValue(it) }
            .addTo(compositeDisposable)
    }

    fun refreshNowPlaying() {
        loadNowPlaying()
    }

    override fun onCleared() {
        super.onCleared()
        viewState = MutableLiveData()
        compositeDisposable.clear()
    }

}