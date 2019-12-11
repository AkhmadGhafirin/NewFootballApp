package com.cascer.newfootballapp.data.network

import com.cascer.newfootballapp.data.response.league.LeagueResponse
import com.cascer.newfootballapp.data.response.match.MatchResponse
import com.cascer.newfootballapp.data.response.match.SearchMatchResponse
import com.cascer.newfootballapp.data.response.team.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("all_leagues.php")
    fun getLeagues(): Observable<LeagueResponse>

    @GET("lookupleague.php")
    fun getDataLeague(
        @Query("id") idLeague: String
    ): Observable<LeagueResponse>

    @GET("eventspastleague.php")
    fun getPastEvent(
        @Query("id") idLeague: String
    ): Observable<MatchResponse>

    @GET("eventsnextleague.php")
    fun getNextEvent(
        @Query("id") idLeague: String
    ): Observable<MatchResponse>

    @GET("searchevents.php")
    fun searchMatch(
        @Query("e") query: String
    ): Observable<SearchMatchResponse>

    @GET("lookupteam.php")
    suspend fun getTeam(
        @Query("id") idLeague: String
    ): TeamResponse
}