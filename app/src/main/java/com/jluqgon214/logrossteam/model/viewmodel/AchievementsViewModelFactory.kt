package com.jluqgon214.logrossteam.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jluqgon214.logrossteam.repository.SteamRepository

class AchievementsViewModelFactory(
    private val repository: SteamRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AchievementsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AchievementsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}