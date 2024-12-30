package com.jluqgon214.logrossteam.model

data class PlayerSummariesResponse(
    val response: PlayerSummariesData
)

data class PlayerSummariesData(
    val players: List<PlayerSummary>
)

data class PlayerSummary(
    val steamid: String,
    val personaname: String,
    val profileurl: String,
    val avatar: String,
    val avatarmedium: String,
    val avatarfull: String,
    val personastate: Int,
    val communityvisibilitystate: Int,
    val profilestate: Int?,
    val lastlogoff: Long,
    val commentpermission: Int?
)