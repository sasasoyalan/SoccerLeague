package com.sssoyalan.soccerleague.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sssoyalan.soccerleague.models.Result

@Dao
interface TeamDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun upsert(result: Result) : Long

     @Query(" SELECT * FROM result")
     fun getAllTeams(): LiveData<List<Result>>

}