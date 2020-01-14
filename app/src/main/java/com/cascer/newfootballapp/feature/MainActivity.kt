package com.cascer.newfootballapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.R.id.*
import com.cascer.newfootballapp.feature.favorite.FavoriteFragment
import com.cascer.newfootballapp.feature.league.LeaguesFragment
import com.cascer.newfootballapp.feature.search.SearchFragment
import com.cascer.newfootballapp.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EspressoIdlingResource.increment()
        setupNavigation()
    }

    private fun setupNavigation() {
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                leagues -> openFragment(LeaguesFragment())
                search -> openFragment(SearchFragment())
                favorites -> openFragment(FavoriteFragment())
            }
            true
        }
        bottom_nav.selectedItemId = leagues
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
