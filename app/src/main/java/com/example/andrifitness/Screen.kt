package com.example.andrifitness

sealed class Screen(val route: String) {
    object MainScreen : Screen(route = "main_screen")
    object WorkoutCreationScreen : Screen(route = "WorkoutCreationScreen")
}
