package com.cascer.newfootballapp.feature.league.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cascer.newfootballapp.feature.league.detail.match.NextMatchFragment
import com.cascer.newfootballapp.feature.league.detail.match.PastMatchFragment
import com.cascer.newfootballapp.feature.league.detail.standings.StandingsFragment
import com.cascer.newfootballapp.feature.league.detail.team.TeamFragment

class LeaguesVPAdapter(fm: FragmentManager, desc: String, idLeague: String) :
    FragmentPagerAdapter(fm) {

    private val pages = listOf(
        DescriptionFragment(desc),
        PastMatchFragment(idLeague),
        NextMatchFragment(idLeague),
        StandingsFragment(idLeague),
        TeamFragment(idLeague)
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "DESCRIPTION"
            1 -> "PAST MATCH"
            2 -> "NEXT MATCH"
            3 -> "STANDINGS"
            else -> "TEAMS"
        }
    }
}