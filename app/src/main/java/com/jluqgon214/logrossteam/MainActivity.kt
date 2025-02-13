package com.jluqgon214.logrossteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.room.Room
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.database.User
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModelFactory
import com.jluqgon214.logrossteam.navigation.AppNavigation
import com.jluqgon214.logrossteam.repository.SteamRepository
import com.jluqgon214.logrossteam.service.SteamApiService
import com.jluqgon214.logrossteam.ui.theme.LogrosSteamTheme

class MainActivity : ComponentActivity() {
    private val viewModel: AchievementsViewModel by viewModels {
        AchievementsViewModelFactory(SteamRepository(SteamApiService.create()))
    }

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()
        enableEdgeToEdge()
        setContent {
            LogrosSteamTheme {
                AppNavigation(viewModel = viewModel, db = db)
            }
        }
    }
}