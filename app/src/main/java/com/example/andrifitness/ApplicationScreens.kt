package com.example.andrifitness

sealed class ApplicationScreens(val route: String) {
    object WorkoutApplicationScreen : ApplicationScreens(route = "main_screen")
    object WorkoutCreationApplicationScreen : ApplicationScreens(route = "WorkoutCreationScreen")
    object ExercisesApplicationScreen : ApplicationScreens(route = "ExercisesScreen")
    object LogsApplicationScreen : ApplicationScreens(route = "LogsScreen")
    object MeasurementsApplicationScreen : ApplicationScreens(route = "MeasurementsScreen")
    object NewWorkoutScreen : ApplicationScreens(route = "NewWorkoutScreen")
    object PreDesignedWorkoutScreen : ApplicationScreens(route = "PreDesignedWorkoutScreen")
    object UserProfileScreen: ApplicationScreens(route = "UserProfileScreen")
    object UserProfileForm: ApplicationScreens(route = "UserProfileForm")
    object WorkoutProgress: ApplicationScreens(route = "WorkoutProgress")
}

