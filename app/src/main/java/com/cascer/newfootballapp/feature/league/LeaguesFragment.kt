package com.cascer.newfootballapp.feature.league

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.feature.MainActivity
import com.cascer.newfootballapp.feature.league.detail.DetailLeagueActivity
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.LeagueViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_leagues.*
import org.koin.android.viewmodel.ext.android.viewModel

class LeaguesFragment : Fragment() {

    private val adapter by lazy { LeaguesAdapter { toDetail(it) } }

    private val viewModel: LeagueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
    }

    private fun setupRV() {
        rv_leagues.layoutManager = LinearLayoutManager(context)
        rv_leagues.adapter = adapter
        requestAndInsert()
    }

    private fun toDetail(data: LeagueEntity) {
        val intent = Intent((context as MainActivity), DetailLeagueActivity::class.java)
        intent.putExtra("league", Gson().toJson(data))
        startActivity(intent)
    }

    private fun requestAndInsert() {
        viewModel.requestLeagues()
        viewModel.getLeagues()
            .observe(this, Observer {
                it?.let {
                    adapter.insertList(it)
                    ShimmerHelper().stopShimmer(shimmer_container)
                }
            })
    }
}