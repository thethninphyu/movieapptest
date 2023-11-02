package com.example.movieapptest.upcoming

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
import kotlinx.android.synthetic.main.upcoming_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private val upcomingViewModel : UpcomingViewModel by viewModel()

    private lateinit var upcomingAdapter: UpcomingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.upcoming_fragment, container, false)
        return view
    }

    companion object{
        fun getUpcomingFragInstance() : UpcomingFragment = UpcomingFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingAdapter = UpcomingAdapter()
        UpcomingRecyclerView.layoutManager = GridLayoutManager(context, 2)
        UpcomingRecyclerView.adapter = upcomingAdapter

        upcomingViewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun render(state: UpcomingViewState){
        when(state){
            is UpcomingViewState.Loading -> renderUpcomingLoadingState()
            is UpcomingViewState.SuccessUpcomingListState -> renderUpcomingSuccessState(state)
            is UpcomingViewState.FailUpcomingListState -> renderUpcomingFailState(state)
        }
    }

    private fun renderUpcomingLoadingState(){
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun renderUpcomingSuccessState(state : UpcomingViewState.SuccessUpcomingListState){

        Toast.makeText(context, "${state.data.size}", Toast.LENGTH_SHORT).show()

        upcomingAdapter.setNewMovieList(state.data)
    }

    private fun renderUpcomingFailState(state: UpcomingViewState.FailUpcomingListState){
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }
}