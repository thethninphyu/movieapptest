package com.example.movieapptest.popular

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
import com.example.movieapptest.nowPlaying.NowPlayingViewState
import kotlinx.android.synthetic.main.popular_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private val popularViewModel : PopularViewModel by viewModel()

    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popular_fragment, container, false)
        return view
    }


    companion object{
        fun getPopularFrag() : PopularFragment = PopularFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularAdapter = PopularAdapter()
        PopularRecyclerView.layoutManager = GridLayoutManager(context, 2)
        PopularRecyclerView.adapter = popularAdapter

        popularViewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun render(state: PopularViewState){
        when(state){
            is PopularViewState.Loading -> renderPopularLoadingState()
            is PopularViewState.SuccessPopularListState -> renderPopularSuccessState(state)
            is PopularViewState.FailPopularListState -> renderPopularFailState(state)
        }
    }

    private fun renderPopularLoadingState() {
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun renderPopularSuccessState(state: PopularViewState.SuccessPopularListState) {
        Toast.makeText(context, "${state.data.size}", Toast.LENGTH_SHORT).show()

        popularAdapter.setNewMovieList(state.data)
    }

    private fun renderPopularFailState(state: PopularViewState.FailPopularListState) {
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }
}