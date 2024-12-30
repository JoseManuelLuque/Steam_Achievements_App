package com.jluqgon214.logrossteam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.utils.formatTimestamp


@Composable
fun Profile(navController: NavController, viewModel: AchievementsViewModel) {
    viewModel.fetchPlayerSummaries(
        "7C799FA749E1088DC8DFEEEC066CA8AA",
        "76561198949068578"
    )

    // Recuperar los datos del perfil de steam
    val playerSummaries by viewModel.playerSummaries.collectAsState()


    // Columna con los datos del perfil de steam
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        playerSummaries?.let { player ->
            // Imagen de Perfil de steam
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter(player.avatarfull),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Datos del perfil de steam
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto con el nombre de usuario
                Text(text = player.personaname)
                // Texto con el estado del usuario
                Text(
                    text = "Estado: ${
                        if (player.personastate == 0) "Desconectado"
                        else if (player.personastate == 1) "En línea"
                        else if (player.personastate == 2) "Ocupado"
                        else if (player.personastate == 3) "Ausente"
                        else if (player.personastate == 4) "No molestar"
                        else "Desconocido"
                    }"
                )
                // Texto con la cantidad de juegos
                Text(text = "Última vez en línea: ${formatTimestamp(player.lastlogoff)}")
                // Texto con la cantidad de amigos
                // Texto con la cantidad de logros
            }
        } ?: run {
            Text(text = "Cargando datos del perfil...")
        }
    }
}