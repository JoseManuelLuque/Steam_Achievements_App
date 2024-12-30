package com.jluqgon214.logrossteam.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel

@Composable
fun GameSelectionScreen(navController: NavHostController, viewModel: AchievementsViewModel) {
    val games = mapOf("The Forest" to "242760", "Rust" to "252490", "Slime Rancher" to "433340")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        games.forEach { (gameName, appId) ->
            Button(
                onClick = {
                    // Llama a fetchAchievements con tus parámetros
                    viewModel.fetchAchievements(
                        "7C799FA749E1088DC8DFEEEC066CA8AA",
                        "76561198949068578",
                        appId
                    )

                    // Guardo el nombre del juego seleccionado
                    viewModel.setSelectedGameName(gameName)

                    // Espera a que la petición se complete
                    navController.navigate("loading")
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = gameName)
            }
        }
    }


}