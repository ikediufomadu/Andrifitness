package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController

@Composable
fun WorkoutLayout(navController: NavController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(pageName.bottom)
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
                onClick = { navController.navigate(ApplicationScreens.ArchivedWorkoutApplicationScreen.route)},
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .requiredWidth(90.dp)
            ) {
                Text(
                    text = "Archive",
                    color = Color.White
                )
            }
            Button(
                onClick = { navController.navigate(ApplicationScreens.WorkoutCreationApplicationScreen.route)},
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .requiredWidth(90.dp)
            ) {
                Text(
                    text = "Edit",
                    color = Color.White
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .layoutId("pageName"),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Workouts", color = Color.White)
        }
        buttonButtons(navController)
    }
}