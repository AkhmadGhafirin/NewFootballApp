package com.cascer.newfootballapp.feature.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.TeamEntity
import com.cascer.newfootballapp.feature.MainActivity
import com.cascer.newfootballapp.feature.league.detail.team.DetailTeamActivity
import com.cascer.newfootballapp.feature.league.detail.team.TeamAdapter
import com.cascer.newfootballapp.utils.EspressoIdlingResource
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.LeagueViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search_team.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchTeamFragment : Fragment() {

    private val adapter by lazy {
        TeamAdapter {
            toDetail(
                it
            )
        }
    }

    private val viewModel: LeagueViewModel by viewModel()

    private var mQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        searchMatch()
    }

    private fun setupRV() {
        rv_search_result.layoutManager = LinearLayoutManager(context)
        rv_search_result.adapter = adapter
        EspressoIdlingResource.decrement()
    }

    private fun toDetail(data: TeamEntity) {
        val intent = Intent((context as MainActivity), DetailTeamActivity::class.java)
        intent.putExtra("team", Gson().toJson(data))
        startActivity(intent)
    }

    private fun searchMatch() {
        getResultSearch()

        et_search_team.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotBlank()) {
                    rv_search_result.visibility = View.VISIBLE
                    mQuery = s.toString()
                    search()
                } else {
                    adapter.insertList(emptyList())
                    rv_search_result.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        et_search_team.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (textView.text.toString().isNotBlank()) {
                    search()
                }
            }
            return@setOnEditorActionListener false
        }

    }

    private fun search() {
        adapter.insertList(emptyList())
        ShimmerHelper().startShimmer(shimmer_container)
        Handler().postDelayed({
            viewModel.setQuerySearchTeam(mQuery)
        }, 3000)
    }

    private fun getResultSearch() {
        viewModel.getResultSearchTeamFromDB().observe(this, Observer {
            adapter.insertList(emptyList())
            if (it.isNotEmpty()) {
                adapter.insertList(it)
                ShimmerHelper().stopShimmer(shimmer_container)
                Log.d("NOT_NULL_SEARCH", it.toString())
            } else {
                viewModel.requestSearchTeamFromService(mQuery).observe(this, Observer { notFound ->
                    if (notFound) tv_not_found.visibility = View.VISIBLE
                    else tv_not_found.visibility = View.GONE
                    ShimmerHelper().stopShimmer(shimmer_container)
                })
                Log.d("NULL_SEARCH", it.toString())
            }
        })
    }
}