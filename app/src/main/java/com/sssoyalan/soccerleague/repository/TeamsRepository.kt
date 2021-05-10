package com.sssoyalan.soccerleague.repository

import com.sssoyalan.soccerleague.api.RetrofitInstance
import com.sssoyalan.soccerleague.db.TeamDatabase
import com.sssoyalan.soccerleague.models.Result

class TeamsRepository(val db : TeamDatabase) {
    suspend fun getTeams() =
        RetrofitInstance.api.getTeams()

    suspend fun upsert(result: Result) = db.getTeamDao().upsert(result)

    fun getAllTeams() = db.getTeamDao().getAllTeams()
}