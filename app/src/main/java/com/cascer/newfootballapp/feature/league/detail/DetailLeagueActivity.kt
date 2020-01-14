package com.cascer.newfootballapp.feature.league.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.utils.GlideApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.league_layout.*

class DetailLeagueActivity : AppCompatActivity() {

    private lateinit var league: LeagueEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        setupView()
    }

    private fun setupView() {
        val leagueAsString = intent.getStringExtra("league")
        league = Gson().fromJson(leagueAsString, LeagueEntity::class.java)
        supportActionBar?.title = league.strLeague
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        GlideApp.with(this).load(league.strBadge).into(iv_league)
        tv_league.text = league.strLeague
        setupTabLayout()
    }

    private fun setupTabLayout() {
        vp_container.adapter =
            LeaguesVPAdapter(supportFragmentManager, league.strDescription ?: "-", league.idLeague)
        tl_league_detail.setupWithViewPager(vp_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
