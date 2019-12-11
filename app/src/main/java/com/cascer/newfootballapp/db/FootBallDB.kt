package com.cascer.newfootballapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.db.entity.MatchEntity

@Database(entities = [LeagueEntity::class, MatchEntity::class], version = 1)
abstract class FootBallDB : RoomDatabase() {

    abstract fun dao(): FootBallDao

    companion object {

        private var INSTANCE: FootBallDB? = null

        fun getDatabase(context: Context): FootBallDB {
            val currentInstance = INSTANCE
            if (currentInstance != null) {
                return currentInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootBallDB::class.java,
                    "football_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}