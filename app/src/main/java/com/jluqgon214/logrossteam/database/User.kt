package com.jluqgon214.logrossteam.database

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val username: String,
    val password: String,
    val steamId: String,
    val apiKey: String
)