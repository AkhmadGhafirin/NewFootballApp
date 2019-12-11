package com.cascer.newfootballapp.data.response.team

import com.cascer.newfootballapp.data.response.team.TeamDetail
import com.google.gson.annotations.SerializedName

data class TeamDetailResponse(

    @field:SerializedName("teams")
    val teams: List<TeamDetail>? = null
)