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
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.feature.MainActivity
import com.cascer.newfootballapp.feature.league.detail.match.DetailMatchActivity
import com.cascer.newfootballapp.feature.league.detail.match.MatchAdapter
import com.cascer.newfootballapp.utils.EspressoIdlingResource
import com.cascer.newfootballapp.utils.ShimmerHelper
import com.cascer.newfootballapp.viewmodel.MatchViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search_match.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchMatchFragment : Fragment() {

    private val adapter by lazy {
        MatchAdapter {
            toDetail(
                it
            )
        }
    }

    private val viewModel: MatchViewModel by viewModel()

    private var mQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_match, container, false)
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

    private fun toDetail(data: MatchEntity) {
        val intent = Intent((context as MainActivity), DetailMatchActivity::class.java)
        intent.putExtra("match", Gson().toJson(data))
        startActivity(intent)
    }

    private fun searchMatch() {
        getResultSearch()

        et_search_match.addTextChangedListener(object : TextWatcher {
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

        et_search_match.setOnEditorActionListener { textView, actionId, _ ->
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
            viewModel.setQuerySearchMatch(mQuery)
        }, 3000)
    }

    private fun getResultSearch() {
        viewModel.getResultSearchMatchFromDB().observe(this, Observer {
            adapter.insertList(emptyList())
            if (it.isNotEmpty()) {
                adapter.insertList(it)
                ShimmerHelper().stopShimmer(shimmer_container)
                Log.d("NOT_NULL_SEARCH", it.toString())
            } else {
                viewModel.requestSearchMatchFromService(mQuery).observe(this, Observer { notFound ->
                    if (notFound) tv_not_found.visibility = View.VISIBLE
                    else tv_not_found.visibility = View.GONE
                    ShimmerHelper().stopShimmer(shimmer_container)
                })
                Log.d("NULL_SEARCH", it.toString())
            }
        })
    }
}