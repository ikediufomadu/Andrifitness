package com.example.andrifitness

sealed class ApplicationScreens(val route: String) {
    object WorkoutApplicationScreen : ApplicationScreens(route = "main_screen")
    object WorkoutCreationApplicationScreen : ApplicationScreens(route = "WorkoutCreationScreen")
    object LogsCreationApplicationScreen : ApplicationScreens(route = "LogsCreationApplicationScreen")
    object ExercisesApplicationScreen : ApplicationScreens(route = "ExercisesScreen")
    object LogsApplicationScreen : ApplicationScreens(route = "LogsScreen")
    object MeasurementsApplicationScreen : ApplicationScreens(route = "MeasurementsScreen")
    object NewWorkoutScreen : ApplicationScreens(route = "NewWorkoutScreen")
    object NewLogsScreen : ApplicationScreens(route = "NewLogsScreen")
    object PreDesignedWorkoutScreen : ApplicationScreens(route = "PreDesignedWorkoutScreen")
    object UserProfileScreen: ApplicationScreens(route = "UserProfileScreen")
    object UserProfileForm: ApplicationScreens(route = "UserProfileForm")
    object WorkoutProgress: ApplicationScreens(route = "WorkoutProgress")
    object MeasurementHistoryScreen: ApplicationScreens(route = "MeasurementHistory")
}

