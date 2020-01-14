package com.cascer.newfootballapp.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.feature.MainActivity
import kotlinx.android.synthetic.main.activity_detail_league.vp_container
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        vp_container.adapter = SearchVPAdapter((activity as MainActivity).supportFragmentManager)
        tl_search.setupWithViewPager(vp_container)
    }
}