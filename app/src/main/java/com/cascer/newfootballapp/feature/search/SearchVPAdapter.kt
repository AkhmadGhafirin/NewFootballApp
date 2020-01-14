package com.cascer.newfootballapp.feature.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SearchVPAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val pages = listOf(
        SearchMatchFragment(),
        SearchTeamFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "SEARCH MATCH"
            else -> "SEARCH TEAM"
        }
    }
}