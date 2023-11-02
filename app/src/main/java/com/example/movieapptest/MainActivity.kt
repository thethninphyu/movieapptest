package com.example.movieapptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.movieapptest.nowPlaying.NowPlayingFragment
import com.example.movieapptest.popular.PopularFragment
import com.example.movieapptest.topRated.TopRatedFragment
import com.example.movieapptest.upcoming.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar : ActionBar

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        fragmentWorks(it.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        toolbar.title = "Now Playing"
        displayFragment(NowPlayingFragment.getNowPlayingFragInstance())

        NavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    private fun fragmentWorks(itemId : Int){
        when(itemId){
            R.id.MenuNowPlaying -> {
                toolbar.title = "Now Playing"
                displayFragment(NowPlayingFragment.getNowPlayingFragInstance())
            }
            R.id.MenuPopular -> {
                toolbar.title = "Popular"
                displayFragment(PopularFragment.getPopularFrag())
            }
            R.id.MenuTopRated -> {
                toolbar.title = "Top Rated"
                displayFragment(TopRatedFragment.getTopRatedFragInstance())
            }
            R.id.MenuUpcoming -> {
                toolbar.title = "Upcoming"
                displayFragment(UpcomingFragment.getUpcomingFragInstance())
            }
            else -> null
        }

    }

    private fun displayFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.ContentLayout, fragment)
            .commit()
    }
}
