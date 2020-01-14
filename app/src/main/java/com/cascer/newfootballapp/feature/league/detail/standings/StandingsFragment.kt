package com.cascer.newfootballapp.feature.league.detail.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.LeagueViewModel
import kotlinx.android.synthetic.main.fragment_standings.*
import org.koin.android.viewmodel.ext.android.viewModel

class StandingsFragment(private val idLeague: String) : Fragment() {

    private val adapter by lazy { StandingsAdapter() }

    private val viewModel: LeagueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStandings()
    }

    private fun setupRV() {
        rv_standings.layoutManager = LinearLayoutManager(context)
        rv_standings.adapter = adapter
    }

    private fun getStandings() {
        setupRV()
        ShimmerHelper().startShimmer(shimmer_container)
        viewModel.requestStandings(idLeague)
            .observe(this, Observer {
                adapter.insertList(it)
                ShimmerHelper().stopShimmer(shimmer_container)
            })
    }

}