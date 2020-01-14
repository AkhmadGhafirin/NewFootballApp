package com.cascer.newfootballapp.data.response.standings

import com.google.gson.annotations.SerializedName

data class Standings(

	@field:SerializedName("loss")
	val loss: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("goalsfor")
	val goalsFor: Int? = null,

	@field:SerializedName("goalsagainst")
	val goalsAgainst: Int? = null,

	@field:SerializedName("teamid")
	val teamId: String? = null,

	@field:SerializedName("goalsdifference")
	val goalsDifference: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("win")
	val win: Int? = null
)