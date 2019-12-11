package com.cascer.newfootballapp.data.response.match

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(

    @field:SerializedName("event")
    val matches: List<Match>? = null
)