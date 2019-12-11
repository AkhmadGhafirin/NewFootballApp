package com.cascer.newfootballapp.feature.league.detail.match

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.utils.GlideApp
import com.cascer.newfootballapp.utils.PopUpMessage
import com.cascer.newfootballapp.viewmodel.MatchViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.match_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMatchActivity : AppCompatActivity() {

    private val viewModel: MatchViewModel by viewModel()

    private var mMenu: Menu? = null
    private var isFavorite: Boolean = false
    private var idEvent = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Match"
        setupView()
    }

    private fun setupView() {
        val matchAsString = intent.getStringExtra("match")
        val data = Gson().fromJson(matchAsString, MatchEntity::class.java)
        tv_schedule.text = data.dateEvent
        tv_score_home.text = data.intHomeScore
        tv_score_away.text = data.intAwayScore
        tv_home.text = data.strHomeTeam
        GlideApp.with(this).load(data.strHomeTeamBadge).into(iv_home)
        GlideApp.with(this).load(data.strAwayTeamBadge).into(iv_away)
        tv_away.text = data.strAwayTeam
        tv_red_home.text = formatString(data.strHomeRedCards)
        tv_red_away.text = formatString(data.strAwayRedCards)
        tv_yellow_home.text = formatString(data.strHomeYellowCards)
        tv_yellow_away.text = formatString(data.strAwayYellowCards)
        tv_goal_home.text = formatString(data.strHomeGoalDetails)
        tv_goal_away.text = formatString(data.strAwayGoalDetails)
        tv_shot_home.text = data.intHomeShots
        tv_shot_away.text = data.intAwayShots
        tv_keeper_home.text = formatString(data.strHomeLineupGoalkeeper)
        tv_keeper_away.text = formatString(data.strAwayLineupGoalkeeper)
        tv_defense_home.text = formatString(data.strHomeLineupDefense)
        tv_defense_away.text = formatString(data.strAwayLineupDefense)
        tv_midfield_home.text = formatString(data.strHomeLineupMidfield)
        tv_midfield_away.text = formatString(data.strAwayLineupMidfield)
        tv_forward_home.text = formatString(data.strHomeLineupForward)
        tv_forward_away.text = formatString(data.strAwayLineupForward)
        tv_substitutes_home.text = formatString(data.strHomeLineupSubstitutes)
        tv_substitutes_away.text = formatString(data.strAwayLineupSubstitutes)
        tv_formation_home.text = data.strHomeFormation
        tv_formation_away.text = data.strAwayFormation

        idEvent = data.idEvent
    }

    private fun formatString(str: String): String {
        return when (str) {
            "-" -> str
            "" -> "-"
            else -> str.replace(";".toRegex(), ";\n").trim()
        }
    }

    private fun addFavorite(): Boolean {
        if (isFavorite) {
            PopUpMessage().popUpToast(application, getString(R.string.remove_favorite_message))
            viewModel.updateFavoriteMatch(false, idEvent)
        } else {
            PopUpMessage().popUpToast(application, getString(R.string.add_favorite_message))
            viewModel.updateFavoriteMatch(true, idEvent)
        }
        return true
    }

    private fun favoriteState() {
        if (isFavorite) {
            mMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
        } else {
            mMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border)
        }
    }

    private fun setupFavorite() {
        viewModel.getFavoriteState(idEvent).observe(this, Observer {
            isFavorite = it
            favoriteState()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu_item, menu)
        mMenu = menu
        setupFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_favorite -> addFavorite()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}