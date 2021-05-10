package com.sssoyalan.soccerleague.api

import com.sssoyalan.soccerleague.models.NewResponse
import retrofit2.Response
import retrofit2.http.GET

interface TeamsAPI {
    @GET("api/teams")
    suspend fun getTeams(): Response<NewResponse>
}