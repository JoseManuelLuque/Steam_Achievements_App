package com.jluqgon214.logrossteam.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jluqgon214.logrossteam.model.achievement.Achievement

@Composable
fun AchievementRow(achievement: Achievement, navController: NavController) {
    achievement?.let {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    // Navegar a la pantalla de detalles del logro
                    navController.navigate("achievementDetail/${it.apiname}")
                }
        ) {
            Image(
                painter = rememberImagePainter(it.icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = it.name ?: "Desconocido", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = if (it.achieved == 1) "Logrado" else "No logrado",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    } ?: run {
        // Maneja el caso donde achievement es null
        Text(text = "Datos del logro no disponibles", style = MaterialTheme.typography.bodyMedium)
    }
}