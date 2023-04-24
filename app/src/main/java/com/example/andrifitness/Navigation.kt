package com.example.andrifitness

import PreDesignedWorkoutLayout
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
        composable(route = ApplicationScreens.ExercisesApplicationScreen.route) {
            ExercisesLayout(navController = navController)
        }
        composable(route = ApplicationScreens.LogsApplicationScreen.route) {
            LogsLayout(navController = navController)
        }
        composable(route = ApplicationScreens.MeasurementsApplicationScreen.route) {
            val viewModel = viewModel<MeasurementViewModel>()
            MeasurementsLayout(navController = navController, viewModel = viewModel)
        }
        composable(route = ApplicationScreens.SettingsApplicationScreen.route) {
            SettingsLayout(navController = navController)
        }
        composable(route = ApplicationScreens.NewWorkoutScreen.route) {
            NewWorkoutLayout(navController = navController)
        }
        composable(route = ApplicationScreens.PreDesignedWorkoutScreen.route) {
            PreDesignedWorkoutLayout(navController = navController)
        }
        composable(route =ApplicationScreens.UserProfileScreen.route){
            UserProfile(navController = navController)
        }
        composable(route = ApplicationScreens.MeasuremntHistoryScreen.route){
            val viewModel = viewModel<MeasurementViewModel>()
            MeasurementHistory(navController = navController, viewModel = viewModel)
        }
    }
}






