package com.example.andrifitness

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .layoutId("bottomButtons"),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate(ApplicationScreens.WorkoutApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(ButtonRequiredHeight)
                .requiredWidth(ButtonRequiredWidth),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBackgroundColor,
                contentColor = ButtonContentColor
            )
        ) {
            Text(text = "Workouts", fontSize = ButtonFontSizes)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.ExercisesApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(ButtonRequiredHeight)
                .requiredWidth(ButtonRequiredWidth),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBackgroundColor,
                contentColor = ButtonContentColor
            )
        ) {
            Text(text = "Exercises", fontSize = ButtonFontSizes)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.LogsApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(ButtonRequiredHeight)
                .requiredWidth(ButtonRequiredWidth),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBackgroundColor,
                contentColor = ButtonContentColor
            )
        ) {
            Text(text = "Logs", fontSize = ButtonFontSizes)
        }
        Button(
            onClick = { navController.navigate(ApplicationScreens.MeasurementsApplicationScreen.route)},
            modifier = Modifier
                .requiredHeight(ButtonRequiredHeight)
                .requiredWidth(ButtonRequiredWidth),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonBackgroundColor,
                contentColor = ButtonContentColor
            )
        ) {
            Text(text = "Measurements", fontSize = ButtonFontSizes)
        }
    }
}
