package com.cascer.newfootballapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league")
data class LeagueEntity(
    @PrimaryKey
    var idLeague: String,
    @ColumnInfo(name = "str_league") var strLeague: String?,
    @ColumnInfo(name = "str_description") var strDescription: String?,
    @ColumnInfo(name = "str_badge") var strBadge: String?
)