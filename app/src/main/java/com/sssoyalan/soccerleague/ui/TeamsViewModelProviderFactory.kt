package com.sssoyalan.soccerleague.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sssoyalan.soccerleague.repository.TeamsRepository

class TeamsViewModelProviderFactory(
    val teamsRepository: TeamsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamsViewModel(teamsRepository) as T
    }
}