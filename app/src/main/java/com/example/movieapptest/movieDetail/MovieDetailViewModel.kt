package com.example.movieapptest.movieDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapptest.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(
    private val repository: MovieDetailRepositoryImpl,
    private val disposable: CompositeDisposable
) : ViewModel() {

    var viewState: MutableLiveData<MovieDetailViewState> = MutableLiveData()

    fun loadMovieDetail(movieId : String){
        repository.loadMovieDetail(movieId)
            .observeOn(Schedulers.io())
            .subscribe { viewState.postValue(it) }
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        viewState = MutableLiveData()
    }

}