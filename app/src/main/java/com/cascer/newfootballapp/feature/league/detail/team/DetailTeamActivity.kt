package com.cascer.newfootballapp.feature.league.detail.team

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.TeamEntity
import com.cascer.newfootballapp.utils.GlideApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.league_layout.*

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var team: TeamEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setupView()
    }

    private fun setupView() {
        val teamAsString = intent.getStringExtra("team")
        team = Gson().fromJson(teamAsString, TeamEntity::class.java)
        supportActionBar?.title = team.strTeam
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        GlideApp.with(this).load(team.strTeamBadge).into(iv_league)
        tv_league.text = team.strTeam
        tv_desc.text = team.strDescription
        tv_league_name.text = "League : ${team.strLeague}"
        tv_alternative.text = "Alternative : ${team.strAlternate}"
        tv_stadium.text = "Stadium : ${team.strStadium}"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
