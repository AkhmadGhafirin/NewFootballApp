package com.cascer.newfootballapp.data.response.team

import com.google.gson.annotations.SerializedName

data class TeamResponse(

    @field:SerializedName("teams")
    val teams: List<Team>? = null
)