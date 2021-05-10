package com.sssoyalan.soccerleague.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "result")
data class Result(
    @PrimaryKey ( autoGenerate = true ) val uId: Int? = null,
    val Team: String,
    val resultId: String,
    val teamId: String,
    val date : String,
    val avatar : String,
    val description : String
) : Serializable