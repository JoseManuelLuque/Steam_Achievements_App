package com.jluqgon214.logrossteam.screens.achievements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.jluqgon214.logrossteam.model.achievement.Achievement

@Composable
fun AchievementDetailScreen(achievement: Achievement) {
    achievement?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(it.icon),
                contentDescription = "Imagen del logro",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.name ?: "Desconocido", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it.description ?: "Descripción no disponible",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } ?: run {
        // Maneja el caso donde achievement es null
        Text(text = "Datos del logro no disponibles", style = MaterialTheme.typography.bodyMedium)
    }
}