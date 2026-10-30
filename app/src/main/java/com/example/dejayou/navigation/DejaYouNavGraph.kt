package com.example.dejayou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.dejayou.screens.HomeScreen
import com.example.dejayou.screens.MomentsScreen
import com.example.dejayou.screens.CalendarScreen
import com.example.dejayou.screens.RemindersScreen
import com.example.dejayou.screens.SettingsScreen

@Composable
fun DejaYouNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(HOME) {
            HomeScreen()
        }

        composable(MOMENTS) {
            MomentsScreen()
        }

        composable(CALENDAR) {
            CalendarScreen()
        }

        composable(REMINDERS) {
            RemindersScreen()
        }

        composable(SETTINGS) {
            SettingsScreen()
        }
    }
}