package com.cascer.newfootballapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class MatchEntity(
    @PrimaryKey @ColumnInfo(name = "id_event") var idEvent: String,
    @ColumnInfo(name = "id_league") var idLeague: String,
    @ColumnInfo(name = "schedule") var schedule: String,
    @ColumnInfo(name = "str_event") var strEvent: String,
    @ColumnInfo(name = "str_sport") var strSport: String,
    @ColumnInfo(name = "str_home_team") var strHomeTeam: String,
    @ColumnInfo(name = "str_away_team") var strAwayTeam: String,
    @ColumnInfo(name = "id_home_Team") var idHomeTeam: String,
    @ColumnInfo(name = "id_away_team") var idAwayTeam: String,
    @ColumnInfo(name = "str_league") var strLeague: String,
    @ColumnInfo(name = "str_season") var strSeason: String,
    @ColumnInfo(name = "int_home_score") var intHomeScore: String,
    @ColumnInfo(name = "int_away_score") var intAwayScore: String,
    @ColumnInfo(name = "str_home_goal_details") var strHomeGoalDetails: String,
    @ColumnInfo(name = "str_away_goal_details") var strAwayGoalDetails: String,
    @ColumnInfo(name = "str_home_red_cards") var strHomeRedCards: String,
    @ColumnInfo(name = "str_away_red_cards") var strAwayRedCards: String,
    @ColumnInfo(name = "str_home_yellow_cards") var strHomeYellowCards: String,
    @ColumnInfo(name = "str_away_yellow_cards") var strAwayYellowCards: String,
    @ColumnInfo(name = "int_home_shots") var intHomeShots: String,
    @ColumnInfo(name = "int_away_shots") var intAwayShots: String,
    @ColumnInfo(name = "str_home_lineup_goalkeeper") var strHomeLineupGoalkeeper: String,
    @ColumnInfo(name = "str_away_lineup_goalkeeper") var strAwayLineupGoalkeeper: String,
    @ColumnInfo(name = "str_home_lineup_defense") var strHomeLineupDefense: String,
    @ColumnInfo(name = "str_away_lineup_defense") var strAwayLineupDefense: String,
    @ColumnInfo(name = "str_home_lineup_midfield") var strHomeLineupMidfield: String,
    @ColumnInfo(name = "str_away_lineup_midfield") var strAwayLineupMidfield: String,
    @ColumnInfo(name = "str_home_lineup_forward") var strHomeLineupForward: String,
    @ColumnInfo(name = "str_away_lineup_forward") var strAwayLineupForward: String,
    @ColumnInfo(name = "str_home_lineup_substitutes") var strHomeLineupSubstitutes: String,
    @ColumnInfo(name = "str_away_lineup_substitutes") var strAwayLineupSubstitutes: String,
    @ColumnInfo(name = "str_home_formation") var strHomeFormation: String,
    @ColumnInfo(name = "str_away_formation") var strAwayFormation: String,
    @ColumnInfo(name = "str_time") var strTime: String,
    @ColumnInfo(name = "date_event") var dateEvent: String,
    @ColumnInfo(name = "str_home_team_badge") var strHomeTeamBadge: String,
    @ColumnInfo(name = "str_away_team_badge") var strAwayTeamBadge: String,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false,
    @ColumnInfo(name = "event_type") var eventType: String
)