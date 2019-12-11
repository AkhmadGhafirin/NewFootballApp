package com.cascer.newfootballapp.feature.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.utils.GlideApp
import kotlinx.android.synthetic.main.league_layout.view.*

class LeaguesAdapter(
    private val listener: (team: LeagueEntity) -> Unit
) : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    private var dataSet = emptyList<LeagueEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.league_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    fun insertList(list: List<LeagueEntity>) {
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

        fun bind(data: LeagueEntity) {
            itemView.apply {
                tv_league.text = data.strLeague
                GlideApp.with(context).load(data.strBadge).into(iv_league)
            }
        }
    }
}