package com.example.movieapptest.nowPlaying

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapptest.R
import kotlinx.android.synthetic.main.nowplaying_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NowPlayingFragment : Fragment() {

    private val nowPlayingViewModel : NowPlayingViewModel by viewModel()

    private lateinit var adapter : NowPlayingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.nowplaying_fragment, container, false)
        return view
    }

    companion object{
        fun getNowPlayingFragInstance() : NowPlayingFragment = NowPlayingFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NowPlayingAdapter()
        NowPlayingRecyclerView.layoutManager = GridLayoutManager(context, 2)
        NowPlayingRecyclerView.adapter = adapter

        nowPlayingViewModel.loadNowPlaying()
        nowPlayingViewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun render(state : NowPlayingViewState) {
        when (state) {
            is NowPlayingViewState.Loading -> renderNowPayingLoadingState()
            is NowPlayingViewState.SuccessNowPlayingListState -> renderNowPlayingSuccessState(state)
            is NowPlayingViewState.FailNowPlayingListState -> renderNowPlayingFailState(state)
        }
    }

    private fun renderNowPayingLoadingState() {
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun renderNowPlayingSuccessState(state: NowPlayingViewState.SuccessNowPlayingListState) {

        Toast.makeText(context, "${state.data.size}", Toast.LENGTH_SHORT).show()

        adapter.setNewMovieList(state.data)

    }

    private fun renderNowPlayingFailState(state: NowPlayingViewState.FailNowPlayingListState) {
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }

}