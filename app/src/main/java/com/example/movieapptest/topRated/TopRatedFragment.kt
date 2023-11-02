package com.example.movieapptest.topRated

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
import kotlinx.android.synthetic.main.toprated_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedFragment : Fragment() {

    private val topRatedViewModel : TopRatedViewModel by viewModel()

    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.toprated_fragment, container, false)
        return view
    }

    companion object{
        fun getTopRatedFragInstance() : TopRatedFragment = TopRatedFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedAdapter = TopRatedAdapter()
        TopRatedRecyclerView.layoutManager = GridLayoutManager(context, 2)
        TopRatedRecyclerView.adapter = topRatedAdapter

        topRatedViewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun render(state: TopRatedViewState){
        when(state){
            is TopRatedViewState.Loading -> renderTopRatedLoadingState()
            is TopRatedViewState.SuccessTopRatedListState -> renderTopRatedSuccessState(state)
            is TopRatedViewState.FailTopRatedListState -> renderTopRatedFailState(state)
        }
    }

    private fun renderTopRatedLoadingState(){
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun renderTopRatedSuccessState(state: TopRatedViewState.SuccessTopRatedListState){
        Toast.makeText(context, "${state.data.size}", Toast.LENGTH_SHORT).show()

        topRatedAdapter.setNewMovieList(state.data)
    }

    private fun renderTopRatedFailState(state: TopRatedViewState.FailTopRatedListState){
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }
}