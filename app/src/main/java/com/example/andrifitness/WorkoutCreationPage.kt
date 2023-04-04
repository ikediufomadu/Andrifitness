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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController

@Composable
fun WorkoutCreationLayout(navController: NavHostController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val createdWorkouts = createRefFor("createdWorkouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(createdWorkouts) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(createdWorkouts.bottom)
        }
    }
    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .layoutId("topButtons"),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate(ApplicationScreens.NewWorkoutScreen.route)
                },
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .requiredWidth(90.dp)
            ) {
                Text(
                    text = "+",
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    navController.navigate(ApplicationScreens.WorkoutApplicationScreen.route)
                          },
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .requiredWidth(90.dp)
            ) {
                Text(
                    text = "Done",
                    color = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .layoutId("pageName"),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Workouts",
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .layoutId("createdWorkouts"),
        ) {

        }
        BottomButtons(navController)
    }
}