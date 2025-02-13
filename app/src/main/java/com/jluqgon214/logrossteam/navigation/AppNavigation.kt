package com.jluqgon214.logrossteam.navigation

import GameSelectionScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.logrossteam.components.BottomNavigationBar
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.database.User
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
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

    // Usuario registrado por defecto
    LaunchedEffect(Unit) {
        val user = User("a", "a", "76561198949068578", "7C799FA749E1088DC8DFEEEC066CA8AA")
        db.userDao().insert(user)
    }


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
                LoadingScreen()
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