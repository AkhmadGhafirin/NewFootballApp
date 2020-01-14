package com.cascer.newfootballapp.data.response.standings

import com.google.gson.annotations.SerializedName

data class StandingsResponse(

	@field:SerializedName("table")
	val table: List<Standings>? = null
)