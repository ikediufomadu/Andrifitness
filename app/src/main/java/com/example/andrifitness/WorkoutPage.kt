package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

@Composable
fun WorkoutLayout(navController: NavController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val workouts = createRefFor("workouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(workouts) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(workouts.bottom)
        }
    }
    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
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
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Workouts",
                fontSize = WTextSize,
                color = WTextColor
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.80f)
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
data class WorkoutEntry(
    val duration: Int,
    val caloriesBurned: Int
)

class WorkoutProgressViewModel : ViewModel() {
    private val _workoutEntries = mutableStateListOf<WorkoutEntry>()
    val workoutEntries: List<WorkoutEntry> get() = _workoutEntries

    fun addWorkoutEntry(entry: WorkoutEntry) {
        _workoutEntries.add(entry)
    }
}
@Composable
fun WorkoutProgress(navController: NavController, viewModel: WorkoutProgressViewModel ) {
    val workoutEntries = viewModel.workoutEntries

    Column {
        Text(text = "Workout Progress", style = MaterialTheme.typography.h4)

        LazyColumn {
            items(workoutEntries) { entry ->
                Text(text = "Duration: ${entry.duration} mins, Calories Burned: ${entry.caloriesBurned}")
            }
        }

        val totalDuration = workoutEntries.sumBy { it.duration }
        val totalCaloriesBurned = workoutEntries.sumBy { it.caloriesBurned }
        Text(text = "Total Duration: $totalDuration mins")
        Text(text = "Total Calories Burned: $totalCaloriesBurned")

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}