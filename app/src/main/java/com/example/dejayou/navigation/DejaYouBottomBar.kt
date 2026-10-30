package com.example.dejayou.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
/* Displays the four navigation tabs and highlights the active one
* The NavController switches between destinations
 */
@Composable
fun DejaYouBottomBar(navController: NavHostController) {

    val screens = listOf(
        "Home",
        "Moments",
        "Calendar",
        "Reminders"
    )

    val routes = listOf(
        HOME,
        MOMENTS,
        CALENDAR,
        REMINDERS
    )
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.List,
        Icons.Filled.DateRange,
        Icons.Filled.Notifications
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar (
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ){
        screens.forEachIndexed { index, screen ->

            NavigationBarItem(
                selected = currentRoute == routes[index],
                onClick = {
                    // Avoid duplicate destinations and preserve state when switching tabs
                    navController.navigate(routes[index]) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(HOME) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = screen
                    )
                },
                label = { Text(screen) }
            )
        }
    }
}