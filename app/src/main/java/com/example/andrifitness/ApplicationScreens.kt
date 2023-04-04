package com.example.andrifitness

sealed class ApplicationScreens(val route: String) {
    object WorkoutApplicationScreen : ApplicationScreens(route = "main_screen")
    object WorkoutCreationApplicationScreen : ApplicationScreens(route = "WorkoutCreationScreen")
    object ArchivedWorkoutApplicationScreen : ApplicationScreens(route = "ArchivedWorkoutScreen")
    object ExercisesApplicationScreen : ApplicationScreens(route = "ExercisesScreen")
    object LogsApplicationScreen : ApplicationScreens(route = "LogsScreen")
    object MeasurementsApplicationScreen : ApplicationScreens(route = "MeasurementsScreen")
    object SettingsApplicationScreen : ApplicationScreens(route = "SettingsScreen")
    object NewWorkoutScreen : ApplicationScreens(route = "NewWorkoutScreen")
}
