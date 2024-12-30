package com.jluqgon214.logrossteam.model.achievement

data class Achievement(
    val apiname: String,
    val achieved: Int,
    val unlocktime: Long,
    val name: String?,
    val description: String?,
    val icon: String?
)