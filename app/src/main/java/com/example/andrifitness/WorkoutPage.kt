package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun WorkoutLayout(navController: NavController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val timer = createRefFor("timer")
        val workouts = createRefFor("workouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(timer) {
            top.linkTo(pageName.bottom)
        }
        constrain(workouts) {
            top.linkTo(timer.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(workouts.bottom)
        }
    }
    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .layoutId("topButtons"),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navController.navigate(ApplicationScreens.PreDesignedWorkoutScreen.route)},
                modifier = Modifier
                    .requiredHeight(WButtonRequiredHeight)
                    .requiredWidth(WButtonRequiredWidth),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text(
                    text = "PreMade",
                    fontSize = WButtonFontSizes,
                    color = WButtonContentColor
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { navController.navigate(ApplicationScreens.WorkoutCreationApplicationScreen.route)},
                modifier = Modifier
                    .requiredHeight(WButtonRequiredHeight)
                    .requiredWidth(WButtonRequiredWidth),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text(
                    text = "Edit",
                    fontSize = WButtonFontSizes,
                    color = WButtonContentColor
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .layoutId("pageName"),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Workouts",
                fontSize = WTextSize,
                color = Color.White
            )
        }
        TimerScreen()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.60f)
                .layoutId("workouts")
        ) {
            DisplayCards(navController)
        }
        Button(onClick = { navController.navigate(ApplicationScreens.WorkoutProgress.route) }) {
            Text(text = "Track Workout Progress")
        }

        BottomButtons(navController)
    }

}

@Composable
fun TimerScreen() {
    var totalTimeSeconds by remember { mutableStateOf(0) }
    var timeRemainingSeconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.DarkGray,
        textColor = Color.White
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.20f)
            .layoutId("timer")

    ) {
        if (!isRunning) {
            // Text input for setting the total time
            TextField(
                value = formatTime(totalTimeSeconds),
                onValueChange = { newValue ->
                    val parts = newValue.split(":")
                    totalTimeSeconds =
                        (parts.getOrNull(0)?.toIntOrNull() ?: 0) * 60 + (parts.getOrNull(1)?.toIntOrNull() ?: 0)
                    timeRemainingSeconds = totalTimeSeconds
                },
                colors = textFieldColors,
                label = {
                    Text(
                        "Total time (format: MM:SS)",
                        color = WTextColor,
                        fontSize = WTextSize
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button to start the timer
            Button(
                onClick = { isRunning = true },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text("Start Timer")
            }
        } else {
            // Text displaying the remaining time
            Text(
                text = formatTime(timeRemainingSeconds),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h4,
                color = WTextColor,
                fontSize = WTextSize
            )
            // Button to stop the timer
            Button(
                onClick = { isRunning = false },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text("Stop Timer")
            }

            // Count down the remaining time
            LaunchedEffect(isRunning) {
                while (isRunning && timeRemainingSeconds > 0) {
                    delay(1000)
                    timeRemainingSeconds--
                }
                isRunning = false
            }
        }
    }
}

private fun formatTime(timeInSeconds: Int): String {
    val minutes = timeInSeconds / 60
    val seconds = timeInSeconds % 60
    return "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}
