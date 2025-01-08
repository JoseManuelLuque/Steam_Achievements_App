package com.jluqgon214.logrossteam.model.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.logrossteam.model.PlayerSummary
import com.jluqgon214.logrossteam.model.achievement.Achievement
import com.jluqgon214.logrossteam.model.ownedGames.GameInfo
import com.jluqgon214.logrossteam.repository.SteamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AchievementsViewModel(private val repository: SteamRepository) : ViewModel() {
    private val _achievements = mutableStateOf<List<Achievement>>(emptyList())
    val achievements: MutableState<List<Achievement>> = _achievements

    fun fetchAchievements(apiKey: String, steamId: String, appId: String) {
        viewModelScope.launch {
            try {
                val fetchedAchievements = repository.getPlayerAchievements(apiKey, steamId, appId)
                _achievements.value = fetchedAchievements
                Log.d("AchievementsViewModel", "Fetched achievements: $fetchedAchievements")
            } catch (e: Exception) {
                Log.e("AchievementsViewModel", "Error fetching achievements", e)
            }
        }
    }

    private val _playerSummaries = MutableStateFlow<PlayerSummary?>(null)
    val playerSummaries: StateFlow<PlayerSummary?> = _playerSummaries

    fun fetchPlayerSummaries(apiKey: String, steamIds: String) {
        viewModelScope.launch {
            val summaries = repository.getPlayerSummaries(apiKey, steamIds)
            _playerSummaries.value = summaries.firstOrNull()
        }
    }

    private val _selectedGameName = mutableStateOf<String?>(null)
    val selectedGameName: MutableState<String?> = _selectedGameName

    fun setSelectedGameName(gameName: String) {
        _selectedGameName.value = gameName
    }

    private val _ownedGames = MutableStateFlow<List<GameInfo>>(emptyList())
    val ownedGames: StateFlow<List<GameInfo>> = _ownedGames

    fun fetchOwnedGames(apiKey: String, steamId: String) {
        viewModelScope.launch {
            val games = repository.getOwnedGames(apiKey, steamId)
            _ownedGames.value = games
        }
    }
}