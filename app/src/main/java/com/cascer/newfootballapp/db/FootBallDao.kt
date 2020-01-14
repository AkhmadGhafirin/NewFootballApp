package com.cascer.newfootballapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.db.entity.TeamEntity

@Dao
interface FootBallDao {

    @Query("SELECT * FROM league ORDER BY str_league")
    fun getLeagues(): LiveData<List<LeagueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(leagueEntity: LeagueEntity)

    @Query("SELECT * FROM team WHERE id_league == :idLeague ORDER BY str_team")
    fun getTeams(idLeague: String): LiveData<List<TeamEntity>>

    @Query("SELECT * FROM team WHERE str_team LIKE :query")
    fun searchTeam(query: String): LiveData<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(teamEntity: TeamEntity)

    @Query("SELECT * FROM `match` WHERE is_favorite == 1 ORDER BY date_event DESC")
    fun getFavoriteMatch(): LiveData<List<MatchEntity>>

    @Query("SELECT is_favorite FROM `match`WHERE id_event LIKE :idEvent")
    fun getFavoriteState(idEvent: String): LiveData<Boolean>

    @Query("UPDATE `match` SET is_favorite = :favorite WHERE id_event LIKE :idEvent")
    fun updateFavoriteMatch(favorite: Boolean, idEvent: String)

    @Query("SELECT * FROM `match` WHERE id_league LIKE :idLeague AND event_type LIKE :eventType ORDER BY date_event DESC")
    fun getMatch(idLeague: String, eventType: String): LiveData<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: MatchEntity)

    @Query("SELECT * FROM `match` WHERE str_event LIKE :query")
    fun searchMatch(query: String): LiveData<List<MatchEntity>>
}