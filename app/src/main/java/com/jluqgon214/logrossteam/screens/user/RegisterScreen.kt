package com.jluqgon214.logrossteam.screens.user

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.R
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.database.User
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    db: AppDatabase,
    onRegister: (String, String, String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var steamId by remember { mutableStateOf("") }
    var apiKey by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text("Registro", style = MaterialTheme.typography.titleLarge)
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.ic_registration),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )
        }

        item {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") }
            )
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        item {
            Column{
                OutlinedTextField(
                    value = steamId,
                    onValueChange = { steamId = it },
                    label = { Text("Steam ID") }
                )
                TextButton(onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW, Uri.parse(
                            "https://store.steampowered.com/account/"
                        )
                    )
                    context.startActivity(intent)
                }) {
                    Text(text = "Mirar tu ID de Steam")
                }
            }
        }
        item {
            Column{
                OutlinedTextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    label = { Text("API Key") }
                )
                TextButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://steamcommunity.com/dev/apikey"
                    ))
                    context.startActivity(intent)
                }) {
                    Text(text = "Obtener tu API Key")
                }
            }
        }
        item {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }

        item {
            Row {
                Button(onClick = {
                    coroutineScope.launch {
                        val existingUser = db.userDao().getUser(username, password)
                        if (existingUser == null) {
                            val user = User(username, password, steamId, apiKey)
                            db.userDao().insert(user)
                            onRegister(username, password, steamId, apiKey)
                            navController.navigate("login")
                        } else {
                            errorMessage = "El usuario ya existe"
                        }
                    }
                }) {
                    Text("Registrarse")
                }
                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Volver al Inicio de sesión")
                }
            }
        }
    }
}