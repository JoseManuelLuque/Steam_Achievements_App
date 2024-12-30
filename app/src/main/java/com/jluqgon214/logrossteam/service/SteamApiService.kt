package com.jluqgon214.logrossteam.service

import com.jluqgon214.logrossteam.model.GameSchemaResponse
import com.jluqgon214.logrossteam.model.PlayerSummariesResponse
import com.jluqgon214.logrossteam.model.achievement.AchievementsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApiService {
    @GET("ISteamUserStats/GetPlayerAchievements/v0001/")
    suspend fun getPlayerAchievements(
        @Query("key") apiKey: String,
        @Query("steamid") steamId: String,
        @Query("appid") appId: String
    ): AchievementsResponse

    @GET("ISteamUserStats/GetSchemaForGame/v2/")
    suspend fun getGameSchema(
        @Query("key") apiKey: String,
        @Query("appid") appId: String,
        @Query("l") language: String? = "spanish"
    ): GameSchemaResponse

    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    suspend fun getPlayerSummaries(
        @Query("key") apiKey: String,
        @Query("steamids") steamIds: String
    ): PlayerSummariesResponse

    companion object {
        private const val BASE_URL = "https://api.steampowered.com/"

        fun create(): SteamApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SteamApiService::class.java)
        }
    }
}