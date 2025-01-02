package com.jluqgon214.logrossteam.screens

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current

    // Reproducir el sonido
    val mediaPlayer = MediaPlayer.create(context, R.raw.splash)

    LaunchedEffect(key1 = true) {
        mediaPlayer.start()
        delay(3000) // Esperar 3 segundos
        navController.popBackStack() // Borrar la pantalla de Splash del trazo de navegación para que no se pueda acceder al el volviendo atras
        navController.navigate("login")
        mediaPlayer.stop()
    }

    Splash()
}

@Composable
fun Splash() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashpng), // Asegúrate de tener un recurso de imagen llamado logo
                contentDescription = "App Logo",
                modifier = Modifier.size(200.dp)
            )
        }
    }

}