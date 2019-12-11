package com.cascer.newfootballapp.data.response.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(

    @field:SerializedName("intHomeShots")
    val intHomeShots: String? = "-",

    @field:SerializedName("strSport")
    val strSport: String? = "-",

    @field:SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = "-",

    @field:SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = "-",

    @field:SerializedName("idLeague")
    val idLeague: String? = "-",

    @field:SerializedName("idSoccerXML")
    val idSoccerXML: String? = "-",

    @field:SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = "-",

    @field:SerializedName("strTVStation")
    val strTVStation: String? = "-",

    @field:SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = "-",

    @field:SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = "-",

    @field:SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = "-",

    @field:SerializedName("idEvent")
    var idEvent: String = "-",

    @field:SerializedName("intRound")
    val intRound: String? = "-",

    @field:SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = "-",

    @field:SerializedName("idHomeTeam")
    var idHomeTeam: String? = "-",

    @field:SerializedName("intHomeScore")
    var intHomeScore: String? = "-",

    @field:SerializedName("dateEvent")
    var dateEvent: String? = "-",

    @field:SerializedName("strCountry")
    val strCountry: String? = "-",

    @field:SerializedName("strAwayTeam")
    var strAwayTeam: String? = "-",

    @field:SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = "-",

    @field:SerializedName("strDate")
    val strDate: String? = "-",

    @field:SerializedName("strHomeFormation")
    val strHomeFormation: String? = "-",

    @field:SerializedName("strMap")
    val strMap: String? = "-",

    @field:SerializedName("idAwayTeam")
    var idAwayTeam: String? = "-",

    @field:SerializedName("strAwayRedCards")
    var strAwayRedCards: String? = "-",

    @field:SerializedName("strBanner")
    val strBanner: String? = "-",

    @field:SerializedName("strFanart")
    val strFanart: String? = "-",

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = "-",

    @field:SerializedName("strResult")
    val strResult: String? = "-",

    @field:SerializedName("strCircuit")
    val strCircuit: String? = "-",

    @field:SerializedName("intAwayShots")
    val intAwayShots: String? = "-",

    @field:SerializedName("strFilename")
    val strFilename: String? = "-",

    @field:SerializedName("strTime")
    var strTime: String? = "-",

    @field:SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = "-",

    @field:SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = "-",

    @field:SerializedName("strLocked")
    val strLocked: String? = "-",

    @field:SerializedName("strSeason")
    val strSeason: String? = "-",

    @field:SerializedName("intSpectators")
    val intSpectators: String? = "-",

    @field:SerializedName("strHomeRedCards")
    var strHomeRedCards: String? = "-",

    @field:SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = "-",

    @field:SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = "-",

    @field:SerializedName("strAwayFormation")
    val strAwayFormation: String? = "-",

    @field:SerializedName("strEvent")
    val strEvent: String? = "-",

    @field:SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = "-",

    @field:SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = "-",

    @field:SerializedName("strHomeTeam")
    var strHomeTeam: String? = "-",

    @field:SerializedName("strThumb")
    val strThumb: String? = "-",

    @field:SerializedName("strLeague")
    val strLeague: String? = "-",

    @field:SerializedName("intAwayScore")
    var intAwayScore: String? = "-",

    @field:SerializedName("strCity")
    val strCity: String? = "-",

    @field:SerializedName("strPoster")
    val strPoster: String? = "-"
) : Parcelable