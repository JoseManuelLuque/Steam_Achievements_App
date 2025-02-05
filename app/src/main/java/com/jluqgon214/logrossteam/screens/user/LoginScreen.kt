package com.jluqgon214.logrossteam.screens.user

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.database.AppDatabase
import kotlinx.coroutines.launch
import com.jluqgon214.logrossteam.R

@Composable
fun LoginScreen(navController: NavController, db: AppDatabase, onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    // Si la ultima vez el usuario eligio recordar, se cargan los datos guardados
    LaunchedEffect(Unit) {
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)
        if (savedUsername != null && savedPassword != null) {
            onLogin(savedUsername, savedPassword)
            navController.popBackStack() // Borrar la pantalla de Login del trazo de navegación para que no se pueda acceder al el volviendo atras
            navController.navigate("gameSelection")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Iniciar Sesión", style = MaterialTheme.typography.titleLarge)
        }

        item{
            Image(
                painter = painterResource(id = R.drawable.ic_login),
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Text("Recordar")
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
                            navController.popBackStack() // Borrar la pantalla de Login del trazo de navegación para que no se pueda acceder al el volviendo atras
                            navController.navigate("gameSelection")
                        } else {
                            errorMessage = "Usuario o contraseña invalidos"
                        }
                    }
                }) {
                    Text("Iniciar Sesion")
                }
                TextButton(onClick = { navController.navigate("register") }) {
                    Text("Registrarse")
                }
            }
        }
    }
}