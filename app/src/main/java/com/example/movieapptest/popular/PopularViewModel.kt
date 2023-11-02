package com.example.movieapptest.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptest.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopularViewModel(
    private val repositoryImpl: PopularRepositoryImpl,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(){

    var viewState : MutableLiveData<PopularViewState> = MutableLiveData()

    init {
        loadPopular()
    }

    private fun loadPopular(){
        repositoryImpl.loadPopular()
            .observeOn(Schedulers.io())
            .subscribe { viewState.postValue(it) }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        viewState = MutableLiveData()
        compositeDisposable.clear()
    }
}