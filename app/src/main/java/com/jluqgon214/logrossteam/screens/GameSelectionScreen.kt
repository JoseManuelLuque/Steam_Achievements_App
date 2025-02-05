import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jluqgon214.logrossteam.components.GameRow
import com.jluqgon214.logrossteam.model.viewmodel.AchievementsViewModel
import com.jluqgon214.logrossteam.screens.LoadingScreen

@Composable
fun GameSelectionScreen(navController: NavHostController, viewModel: AchievementsViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    val games by viewModel.ownedGames.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchOwnedGames("7C799FA749E1088DC8DFEEEC066CA8AA", "76561198949068578").let {
            isLoading = false
        }
    }

    if (isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .testTag("SearchField"),
                label = { Text("Buscar juego") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }
            )

            val filteredGames = games.filter { it.name.contains(searchQuery, ignoreCase = true) }

            LazyColumn {
                items(filteredGames) { game ->
                    GameRow(game, navController, viewModel)
                }
            }
        }
    }
}