package com.cascer.newfootballapp.feature.league.detail.team

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.TeamEntity
import com.cascer.newfootballapp.feature.league.detail.DetailLeagueActivity
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.LeagueViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeamFragment(private val idLeague: String) : Fragment() {

    private val adapter by lazy {
        TeamAdapter { toDetail(it) }
    }

    private val viewModel: LeagueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStandings()
    }

    private fun toDetail(data: TeamEntity) {
        val intent = Intent((context as DetailLeagueActivity), DetailTeamActivity::class.java)
        intent.putExtra("team", Gson().toJson(data))
        startActivity(intent)
    }

    private fun setupRV() {
        rv_team.layoutManager = LinearLayoutManager(context)
        rv_team.adapter = adapter
    }

    private fun getStandings() {
        setupRV()
        ShimmerHelper().startShimmer(shimmer_container)
        viewModel.requestTeamsFromService(idLeague)
        viewModel.getTeams(idLeague)
            .observe(this, Observer {
                adapter.insertList(it)
                ShimmerHelper().stopShimmer(shimmer_container)
            })
    }

}