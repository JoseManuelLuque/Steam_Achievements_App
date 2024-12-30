package com.jluqgon214.logrossteam.screens.user

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.database.AppDatabase
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, db: AppDatabase, onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    // Check if user is already remembered
    LaunchedEffect(Unit) {
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)
        if (savedUsername != null && savedPassword != null) {
            onLogin(savedUsername, savedPassword)
            navController.navigate("gameSelection") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Remember Me")
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
        Button(onClick = {
            coroutineScope.launch {
                val user = db.userDao().getUser(username, password)
                if (user != null) {
                    if (rememberMe) {
                        sharedPreferences.edit().apply {
                            putString("username", username)
                            putString("password", password)
                            apply()
                        }
                    }
                    onLogin(username, password)
                    navController.navigate("gameSelection") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    errorMessage = "Invalid username or password"
                }
            }
        }) {
            Text("Login")
        }
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Register")
        }
    }
}