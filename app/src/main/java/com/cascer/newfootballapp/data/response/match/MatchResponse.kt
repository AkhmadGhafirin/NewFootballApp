package com.cascer.newfootballapp.data.response.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(

	@field:SerializedName("events")
	val matches: List<Match>? = null
)