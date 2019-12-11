package com.cascer.newfootballapp.data.response.league

import com.cascer.newfootballapp.data.response.league.League
import com.google.gson.annotations.SerializedName

data class LeagueResponse(

	@field:SerializedName("leagues")
	val leagues: List<League>? = null
)