package com.sssoyalan.soccerleague.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sssoyalan.soccerleague.models.NewResponse
import com.sssoyalan.soccerleague.models.Result
import com.sssoyalan.soccerleague.repository.TeamsRepository
import com.sssoyalan.soccerleague.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class TeamsViewModel(
    val teamsRepository : TeamsRepository
): ViewModel() {

    val teams : MutableLiveData<Resource<NewResponse>> = MutableLiveData()

    init {
        getTeams()
    }

    fun getTeams() = viewModelScope.launch {
        teams.postValue(Resource.Loading())
        val  response = teamsRepository.getTeams()
        teams.postValue(handleTeamsResponse(response))
    }

    private fun handleTeamsResponse(response: Response<NewResponse>) : Resource<NewResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveResult(result: Result) =viewModelScope.launch {
        teamsRepository.upsert(result)
    }

    fun getAllTeams() = teamsRepository.getAllTeams()
}