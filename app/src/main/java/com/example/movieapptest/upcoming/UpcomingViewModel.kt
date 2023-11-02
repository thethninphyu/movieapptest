package com.example.movieapptest.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptest.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpcomingViewModel(
    private val repositoryImpl: UpcomingRepositoryImpl,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    var viewState : MutableLiveData<UpcomingViewState> = MutableLiveData()

    init {
        loadUpcoming()
    }

    private fun loadUpcoming(){
        repositoryImpl.loadUpcoming()
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