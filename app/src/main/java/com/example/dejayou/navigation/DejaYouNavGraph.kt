package com.example.dejayou.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dejayou.screens.CalendarScreen
import com.example.dejayou.screens.HomeScreen
import com.example.dejayou.screens.MomentsScreen
import com.example.dejayou.screens.RemindersScreen
import com.example.dejayou.screens.SettingsScreen
import com.example.dejayou.ui.viewmodel.MomentViewModel
/* Defines the apps navigation graph and manages the bottom bar
 *The bottom bar only appears on the four main screens
 * NC
 */
@Composable
fun DejaYouNavGraph(
    momentViewModel: MomentViewModel
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    Scaffold (containerColor = Color.Transparent,
        bottomBar = {
            //See the current Route to decide when to show the bottom bar.
            if( currentRoute == HOME ||
                currentRoute == MOMENTS||
                currentRoute == CALENDAR||
                currentRoute == REMINDERS
            ) {
            DejaYouBottomBar(navController)
        }
}
    ){ innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = HOME
        ) {
            // Opens Settings from the Home screen
            composable(HOME) {
                HomeScreen(
                    onSettingsClick = {
                        navController.navigate(SETTINGS)
                    },
                    momentViewModel = momentViewModel
                )
            }

            composable(MOMENTS) {
                MomentsScreen(
                    momentViewModel = momentViewModel
                )
            }

            composable(CALENDAR) {
                CalendarScreen()
            }

            composable(REMINDERS) {
                RemindersScreen()
            }
            // Returns to the previous destination when the back button is pressed
            composable(SETTINGS) {
                SettingsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}