package com.jluqgon214.logrossteam.Pruebas

import GameSelectionScreen
import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.jluqgon214.logrossteam.database.AppDatabase
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.repository.SteamRepository
import com.jluqgon214.logrossteam.screens.LoadingScreen
import com.jluqgon214.logrossteam.screens.Profile
import com.jluqgon214.logrossteam.screens.achievements.AchievementsScreen
import com.jluqgon214.logrossteam.screens.user.LoginScreen
import com.jluqgon214.logrossteam.service.SteamApiService
import com.jluqgon214.logrossteam.ui.theme.LogrosSteamTheme
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.coroutineContext

class PruebasUI {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context = ApplicationProvider.getApplicationContext<Context>()

    // TEST 1: Verificar que la pantalla de selección de juegos muestra los juegos
    @Test
    fun test_GameSelectionScreenDisplaysGames() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                GameSelectionScreen(
                    navController = rememberNavController(),
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create()))
                )
            }
        }
        composeTestRule.onNodeWithText("The Forest").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rust").assertIsDisplayed()
        composeTestRule.onNodeWithText("Slime Rancher").assertIsDisplayed()
    }

    // TEST 2: Verificar que el campo de búsqueda esté visible
    @Test
    fun test_SearchFieldIsDisplayed() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                GameSelectionScreen(
                    navController = rememberNavController(),
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create()))
                )
            }
        }
        composeTestRule.onNodeWithTag("SearchField").assertIsDisplayed()
    }

    // TEST 3: Verificar la funcionalidad de entrada de texto en el campo de búsqueda
    @Test
    fun test_SearchFieldInput() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                GameSelectionScreen(
                    navController = rememberNavController(),
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create()))
                )
            }
        }
        val searchText = "Rust"
        composeTestRule.onNodeWithTag("SearchField").performTextInput(searchText)
        composeTestRule.onNodeWithTag("SearchField").assertTextEquals(searchText)
    }

    // TEST 4: Verificar que el botón de inicio de sesión realiza la acción correcta
@Test
fun test_LoginButtonFunctionality() {
    composeTestRule.setContent {
        LogrosSteamTheme {
            LoginScreen(
                navController = rememberNavController(),
                db = AppDatabase.getInstance(context)
            ) { username, password ->
                // Simula la acción de inicio de sesión
                if (username == "testUser" && password == "testPass") {
                    // Acción de inicio de sesión exitosa
                    //composeTestRule.
                } else {
                    // Acción de inicio de sesión fallida
                }
            }
        }
    }
    composeTestRule.onNodeWithText("Usuario").performTextInput("testUser")
    composeTestRule.onNodeWithText("Contraseña").performTextInput("testPass")
    composeTestRule.onNodeWithText("Iniciar Sesion").performClick()
    // Aquí puedes verificar el resultado de la acción de inicio de sesión
    composeTestRule.onNodeWithText("Login Successful").assertIsDisplayed()
}

    // TEST 5: Verificar que la pantalla de carga se muestra correctamente
    @Test
    fun test_LoadingScreenIsDisplayed() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                LoadingScreen()
            }
        }
        composeTestRule.onNodeWithText("Cargando...").assertIsDisplayed()
    }

    // TEST 6: Verificar que se muestra el mensaje de error en el login
    @Test
    fun test_LoginErrorMessage() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                LoginScreen(
                    navController = rememberNavController(),
                    db = AppDatabase.getInstance(context)
                ) { _, _ -> }
            }
        }
        composeTestRule.onNodeWithText("Iniciar Sesion").performClick()
        composeTestRule.onNodeWithText("Usuario o contraseña invalidos").assertIsDisplayed()
    }

    // TEST 7: Verificar que se navega a la pantalla de registro desde el login
    @Test
    fun test_NavigateToRegisterScreen() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                LoginScreen(
                    navController = rememberNavController(),
                    db = AppDatabase.getInstance(context)
                ) { _, _ -> }
            }
        }
        composeTestRule.onNodeWithText("Registrarse").performClick()
        composeTestRule.onNodeWithText("Register").assertIsDisplayed()
    }

    // TEST 8: Verificar que se muestra el perfil del usuario
    @Test
    fun testProfileScreenIsDisplayed() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                Profile(
                    navController = rememberNavController(),
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create()))
                )
            }
        }
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
    }

    // TEST 9: Verificar que se muestran los logros completados
    @Test
    fun test_CompletedAchievementsDisplayed() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                AchievementsScreen(
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create())),
                    innerPadding = PaddingValues(),
                    navController = rememberNavController()
                )
            }
        }
        composeTestRule.onNodeWithText("Completados").performClick()
        composeTestRule.onNodeWithText("Achievement 1").assertIsDisplayed()
    }

    // TEST 10: Verificar que se muestran los logros restantes
    @Test
    fun test_RemainingAchievementsDisplayed() {
        composeTestRule.setContent {
            LogrosSteamTheme {
                AchievementsScreen(
                    viewModel = AchievementsViewModel(SteamRepository(SteamApiService.create())),
                    innerPadding = PaddingValues(),
                    navController = rememberNavController()
                )
            }
        }
        composeTestRule.onNodeWithText("Restantes").performClick()
        composeTestRule.onNodeWithText("Achievement 2").assertIsDisplayed()
    }
}