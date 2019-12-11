package com.cascer.newfootballapp.feature.favorite

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
import com.cascer.newfootballapp.feature.MainActivity
import com.cascer.newfootballapp.feature.league.detail.match.DetailMatchActivity
import com.cascer.newfootballapp.feature.league.detail.match.MatchAdapter
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.MatchViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_leagues.shimmer_container
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
    }

    private fun setupRV() {
        rv_favorite.layoutManager = LinearLayoutManager(context)
        rv_favorite.adapter = adapter
        getFavorite()
    }

    private fun toDetail(data: MatchEntity) {
        val intent = Intent((context as MainActivity), DetailMatchActivity::class.java)
        intent.putExtra("match", Gson().toJson(data))
        startActivity(intent)
    }

    private fun getFavorite() {
        viewModel.getFavoriteMatch()
            .observe(this, Observer {
                rv_favorite.visibility = View.INVISIBLE
                if (it.isNotEmpty()) {
                    rv_favorite.visibility = View.VISIBLE
                    tv_empty.visibility = View.GONE
                    adapter.insertList(it)
                    ShimmerHelper().stopShimmer(shimmer_container)
                } else {
                    tv_empty.visibility = View.VISIBLE
                    ShimmerHelper().stopShimmer(shimmer_container)
                }
            })
    }
}