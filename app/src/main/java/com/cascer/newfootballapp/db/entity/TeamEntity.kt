package com.cascer.newfootballapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey @ColumnInfo(name = "id_team") val idTeam: String,
    @ColumnInfo(name = "str_description") val strDescription: String? = "-",
    @ColumnInfo(name = "str_alternate") val strAlternate: String? = "-",
    @ColumnInfo(name = "str_team") val strTeam: String? = "-",
    @ColumnInfo(name = "str_stadium") val strStadium: String? = "-",
    @ColumnInfo(name = "str_team_badge") val strTeamBadge: String? = "-",
    @ColumnInfo(name = "str_league") val strLeague: String? = "-",
    @ColumnInfo(name = "id_league") val idLeague: String? = "-"
)