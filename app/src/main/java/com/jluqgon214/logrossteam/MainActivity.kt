package com.jluqgon214.logrossteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogrosSteamTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}