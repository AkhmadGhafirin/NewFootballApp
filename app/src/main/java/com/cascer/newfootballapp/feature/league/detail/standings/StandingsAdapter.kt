package com.cascer.newfootballapp.feature.league.detail.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.data.response.standings.Standings
import kotlinx.android.synthetic.main.standings_layout.view.*

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    private var dataSet = emptyList<Standings>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.standings_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun insertList(list: List<Standings>) {
        dataSet = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Standings) {
            itemView.apply {
                tv_team_name.text = data.name
                tv_team_played.text = data.played.toString()
                tv_team_goals_for.text = data.goalsFor.toString()
                tv_team_goals_against.text = data.goalsAgainst.toString()
                tv_team_goals_difference.text = data.goalsDifference.toString()
                tv_team_win.text = data.win.toString()
                tv_team_draw.text = data.draw.toString()
                tv_team_loss.text = data.loss.toString()
                tv_team_total.text = data.total.toString()
            }
        }
    }
}