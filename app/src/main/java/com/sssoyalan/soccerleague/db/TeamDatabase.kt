package com.sssoyalan.soccerleague.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sssoyalan.soccerleague.models.Result

@Database(entities = [Result::class], version = 1)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun getTeamDao(): TeamDao

    companion object {
        @Volatile
        private var instance: TeamDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TeamDatabase::class.java,
                "teams_db.db"
            ).build()
    }
}