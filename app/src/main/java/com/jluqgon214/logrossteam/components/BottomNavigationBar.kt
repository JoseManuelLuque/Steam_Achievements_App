package com.jluqgon214.logrossteam.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jluqgon214.logrossteam.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Inicio", "Logros", "Perfil")
    val selectedIcons =
        listOf(
            R.drawable.ic_home,
            R.drawable.ic_achievements,
            R.drawable.ic_profile
        )
    val unselectedIcons = listOf(
        R.drawable.ic_home_filled,
        R.drawable.ic_achievements_filled,
        R.drawable.ic_profile_filled
    )
    val routes = listOf("gameSelection", "achievements", "profile")

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.Black,

        ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (selectedItem == index) unselectedIcons[index] else selectedIcons[index]
                        ),
                        contentDescription = item,
                    )
                },
                label = {
                    if (selectedItem == index) {
                        Text(
                            item,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        )
                    } else {
                        Text(item, style = TextStyle(fontSize = 12.sp))
                    }

                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(routes[index])
                }/*,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = VerdeOscuro,
                    selectedIconColor = ColorIconos,
                    selectedTextColor = ColorTextoSeleccionado,
                    unselectedTextColor = ColorTextoSecundario,
                    unselectedIconColor = ColorIconos2
                )*/
            )
        }
    }
}