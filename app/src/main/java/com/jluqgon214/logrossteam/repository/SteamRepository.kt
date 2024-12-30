package com.jluqgon214.logrossteam.repository

import com.jluqgon214.logrossteam.model.PlayerSummary
import com.jluqgon214.logrossteam.model.achievement.Achievement
import com.jluqgon214.logrossteam.service.SteamApiService

class SteamRepository(private val apiService: SteamApiService) {
    suspend fun getPlayerAchievements(
        apiKey: String,
        steamId: String,
        appId: String
    ): List<Achievement> {
        val playerAchievementsResponse = apiService.getPlayerAchievements(apiKey, steamId, appId)
        val gameSchemaResponse = apiService.getGameSchema(apiKey, appId, "spanish")

        val playerAchievements = playerAchievementsResponse.playerstats.achievements
        val achievementDetailsMap =
            gameSchemaResponse.game.availableGameStats.achievements.associateBy { it.name }

        return playerAchievements.map { achievement ->
            val details = achievementDetailsMap[achievement.apiname]
            Achievement(
                apiname = achievement.apiname,
                achieved = achievement.achieved,
                unlocktime = achievement.unlocktime,
                name = details?.displayName,
                description = details?.description,
                icon = details?.icon
            )
        }
    }

    suspend fun getPlayerSummaries(apiKey: String, steamIds: String): List<PlayerSummary> {
        val playerSummariesResponse = apiService.getPlayerSummaries(apiKey, steamIds)
        return playerSummariesResponse.response.players
    }
}