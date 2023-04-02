package com.example.andrifitness

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ApplicationScreens.WorkoutApplicationScreen.route) {
        composable(route = ApplicationScreens.WorkoutApplicationScreen.route) {
            WorkoutLayout(navController = navController)
        }
        composable(route = ApplicationScreens.WorkoutCreationApplicationScreen.route) {
            WorkoutCreationLayout(navController = navController)
        }
        composable(route = ApplicationScreens.ArchivedWorkoutApplicationScreen.route) {
            ArchivedWorkoutLayout(navController = navController)
        }
        composable(route = ApplicationScreens.ExercisesApplicationScreen.route) {
            ExercisesLayout(navController = navController)
        }
        composable(route = ApplicationScreens.LogsApplicationScreen.route) {
            LogsLayout(navController = navController)
        }
        composable(route = ApplicationScreens.MeasurementsApplicationScreen.route) {
            MeasurementsLayout(navController = navController)
        }
        composable(route = ApplicationScreens.SettingsApplicationScreen.route) {
            SettingsLayout(navController = navController)
        }
    }
}







