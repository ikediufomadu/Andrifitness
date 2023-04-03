package com.example.andrifitness

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BottomButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f)
            .layoutId("bottomButtons"),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.navigate(ApplicationScreens.WorkoutApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(40.dp)
                .requiredWidth(60.dp)
        ) {
            Text(text = "Workouts", color = Color.White, fontSize = 10.sp)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.ExercisesApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(40.dp)
                .requiredWidth(60.dp)
        ) {
            Text(text = "Exercises", color = Color.White, fontSize = 10.sp)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.LogsApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(40.dp)
                .requiredWidth(60.dp)
        ) {
            Text(text = "Logs", color = Color.White, fontSize = 10.sp)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.MeasurementsApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(40.dp)
                .requiredWidth(60.dp)
        ) {
            Text(text = "Measurements", color = Color.White, fontSize = 10.sp)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.SettingsApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(40.dp)
                .requiredWidth(60.dp)
        ) {
            Text(text = "Settings", color = Color.White, fontSize = 10.sp)
        }
    }
}
