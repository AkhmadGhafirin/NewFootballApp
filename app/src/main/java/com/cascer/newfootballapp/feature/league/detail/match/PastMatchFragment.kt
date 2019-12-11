package com.cascer.newfootballapp.feature.league.detail.match

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.feature.league.detail.DetailLeagueActivity
import com.cascer.newfootballapp.utils.PAST_MATCH
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.MatchViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_past_match.*
import org.koin.android.viewmodel.ext.android.viewModel

class PastMatchFragment(private val idLeague: String) : Fragment() {

    private val adapter by lazy {
        MatchAdapter {
            toDetail(
                it
            )
        }
    }

    private val viewModel: MatchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_past_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        getMatch()
    }

    private fun setupRV() {
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = adapter
    }

    private fun toDetail(data: MatchEntity) {
        val intent = Intent((context as DetailLeagueActivity), DetailMatchActivity::class.java)
        intent.putExtra("match", Gson().toJson(data))
        startActivity(intent)
    }

    private fun requestMatch() {
        ShimmerHelper().startShimmer(shimmer_container)
        rv_match.visibility = View.INVISIBLE
        viewModel.requestPastMatchFromService(idLeague)
    }

    private fun getMatch() {
        requestMatch()
        viewModel.getMatch(idLeague, PAST_MATCH)
            .observe(this, Observer {
                rv_match.visibility = View.INVISIBLE
                if (it.isNotEmpty()) {
                    tv_not_available.visibility = View.GONE
                    adapter.insertList(it)
                    rv_match.visibility = View.VISIBLE
                    ShimmerHelper().stopShimmer(shimmer_container)
                } else {
                    tv_not_available.visibility = View.VISIBLE
                    ShimmerHelper().stopShimmer(shimmer_container)
                }
            })
    }

}