package com.jluqgon214.logrossteam.screens.achievements

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jluqgon214.logrossteam.components.AchievementRow
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.ui.theme.Blanco

@Composable
fun AchievementsScreen(
    viewModel: AchievementsViewModel,
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    val achievements by viewModel.achievements
    var showCompleted by remember { mutableStateOf(true) }

    // Manejar la acción de volver atrás
    BackHandler {
        navController.popBackStack("gameSelection", inclusive = false)
    }

    if (achievements.isEmpty()) {
        // Maneja el caso donde achievements es null
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Datos de logros no disponibles")
            Text(text = "En el caso de no haber seleccionado nigun juego, seleccione uno")
        }
    } else {
        // Muestra la lista de logros
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
        ) {
            //Titulo Juego
            Text(
                text = "Logros de ${viewModel.selectedGameName.value.toString()}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )

            // Lista de logros filtrados por si estan obtenidos o no
            LazyColumn(contentPadding = innerPadding, modifier = Modifier.weight(8f)) {
                val filteredAchievements =
                    achievements.filter { it.achieved == if (showCompleted) 1 else 0 }
                items(filteredAchievements) { achievement ->
                    AchievementRow(achievement = achievement, navController = navController)
                }
            }

            // Botones para filtrar logros
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { showCompleted = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Blanco
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {

                    Text("Completados", textAlign = TextAlign.Center)

                }

                VerticalDivider()

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { showCompleted = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Blanco
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text("Restantes", textAlign = TextAlign.Center)
                }
            }
        }
    }
}