package com.jluqgon214.logrossteam.model

data class GameSchemaResponse(
    val game: Game
)

data class Game(
    val availableGameStats: AvailableGameStats
)

data class AvailableGameStats(
    val achievements: List<AchievementDetails>
)

data class AchievementDetails(
    val name: String,
    val displayName: String,
    val description: String,
    val icon: String
)