package com.cascer.newfootballapp.feature.league.detail.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.utils.GlideApp
import kotlinx.android.synthetic.main.match_layout.view.*

class MatchAdapter(
    private val listener: (team: MatchEntity) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    private var dataSet = emptyList<MatchEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.match_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun insertList(list: List<MatchEntity>) {
        dataSet = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.invoke(dataSet[adapterPosition])
            }
        }

        fun bind(data: MatchEntity) {
            itemView.apply {
                tv_schedule.text = data.dateEvent
                tv_home.text = data.strHomeTeam
                tv_score_home.text = data.intHomeScore
                tv_score_away.text = data.intAwayScore
                tv_away.text = data.strAwayTeam
                GlideApp.with(context).load(data.strHomeTeamBadge).into(iv_home)
                GlideApp.with(context).load(data.strAwayTeamBadge).into(iv_away)
            }
        }
    }
}