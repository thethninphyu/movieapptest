package com.example.movieapptest.topRated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptest.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel(
    private val repositoryImpl: TopRatedRepositoryImpl,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    var viewState : MutableLiveData<TopRatedViewState> = MutableLiveData()

    init {
        loadTopRated()
    }

    private fun loadTopRated(){
        repositoryImpl.loadTopRated()
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