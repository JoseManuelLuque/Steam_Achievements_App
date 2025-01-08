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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.jluqgon214.logrossteam.model.ownedGames.GameInfo
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel

@Composable
fun GameRow(game: GameInfo, navController: NavHostController, viewModel: AchievementsViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                viewModel.fetchAchievements(
                    "7C799FA749E1088DC8DFEEEC066CA8AA",
                    "76561198949068578",
                    game.appid.toString()
                )
                viewModel.setSelectedGameName(game.name)
                navController.navigate("achievements")
            }
    ) {
        Image(
            painter = rememberImagePainter("https://media.steampowered.com/steamcommunity/public/images/apps/${game.appid.toString()}/${game.img_icon_url}.jpg"),
            contentDescription = null,
            modifier = Modifier.size(38.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = game.name)
            Text(text = "Tiempo jugado: ${game.playtime_forever / 60} Horas")
        }
    }
}