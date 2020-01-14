package com.cascer.newfootballapp.feature.league.detail.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.TeamEntity
import com.cascer.newfootballapp.utils.GlideApp
import kotlinx.android.synthetic.main.league_layout.view.*

class TeamAdapter(
    private val listener: (team: TeamEntity) -> Unit
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var dataSet = emptyList<TeamEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.league_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun insertList(list: List<TeamEntity>) {
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

        fun bind(data: TeamEntity) {
            itemView.apply {
                tv_league.text = data.strTeam
                GlideApp.with(context).load(data.strTeamBadge).into(iv_league)
            }
        }
    }
}