package com.jluqgon214.logrossteam.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.database.User
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, db: AppDatabase, onRegister: (String, String, String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var steamId by remember { mutableStateOf("") }
    var apiKey by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(
            value = steamId,
            onValueChange = { steamId = it },
            label = { Text("Steam ID") }
        )
        TextField(
            value = apiKey,
            onValueChange = { apiKey = it },
            label = { Text("API Key") }
        )
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
        Button(onClick = {
            coroutineScope.launch {
                val existingUser = db.userDao().getUser(username, password)
                if (existingUser == null) {
                    val user = User(username, password, steamId, apiKey)
                    db.userDao().insert(user)
                    onRegister(username, password, steamId, apiKey)
                    navController.navigate("login")
                } else {
                    errorMessage = "Username already exists"
                }
            }
        }) {
            Text("Register")
        }
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Back to Login")
        }
    }
}