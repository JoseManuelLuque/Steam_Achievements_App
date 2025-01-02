package com.jluqgon214.logrossteam.navigation

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.logrossteam.R
import com.jluqgon214.logrossteam.components.BottomNavigationBar
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.screens.GameSelectionScreen
import com.jluqgon214.logrossteam.screens.LoadingScreen
import com.jluqgon214.logrossteam.screens.Profile
import com.jluqgon214.logrossteam.screens.SplashScreen
import com.jluqgon214.logrossteam.screens.achievements.AchievementDetailScreen
import com.jluqgon214.logrossteam.screens.achievements.AchievementsScreen
import com.jluqgon214.logrossteam.screens.user.LoginScreen
import com.jluqgon214.logrossteam.screens.user.RegisterScreen

@Composable
fun AppNavigation(viewModel: AchievementsViewModel, db: AppDatabase) {
    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController)
            }

            composable("gameSelection") {
                GameSelectionScreen(navController, viewModel)
            }
            composable("achievements") {
                AchievementsScreen(viewModel, innerPadding, navController)
            }
            composable("achievementDetail/{achievementId}") { backStackEntry ->
                val achievementId = backStackEntry.arguments?.getString("achievementId")
                val achievement = viewModel.achievements.value.find { it.apiname == achievementId }
                achievement?.let { AchievementDetailScreen(it) }
            }
            composable("loading") {
                LoadingScreen(navController)
            }

            composable("profile") {
                Profile(navController, viewModel)
            }

            composable("login") {
                LoginScreen(navController, db) { username, password ->
                    // Lógica después del login
                    navController.navigate("gameSelection")
                }
            }
            composable("register") {
                RegisterScreen(navController, db) { username, password, steamId, apiKey ->
                    // Lógica después del registro
                }
            }
        }
    }
}