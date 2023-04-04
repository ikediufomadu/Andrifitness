package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController

@Composable
fun WorkoutCreationLayout(navController: NavHostController) {

    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val createdWorkout = createRefFor("createdWorkout")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(createdWorkout) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(createdWorkout.bottom)
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
                    .requiredHeight(WButtonRequiredHeight)
                    .requiredWidth(WButtonRequiredWidth),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text(
                    text = "+",
                    fontSize = WButtonFontSizes
                )
            }
            Button(
                onClick = {
                    navController.navigate(ApplicationScreens.WorkoutApplicationScreen.route)
                          },
                modifier = Modifier
                    .requiredHeight(WButtonRequiredHeight)
                    .requiredWidth(WButtonRequiredWidth),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WButtonBackgroundColor,
                    contentColor = WButtonContentColor
                )
            ) {
                Text(
                    text = "Done",
                    fontSize = WButtonFontSizes
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
                .fillMaxHeight(.85f)
                .layoutId("createdWorkout")
         ) {
            DisplayCards()
        }

        BottomButtons(navController)
    }
}

@Composable
fun DisplayCards() {
    CardList.forEach { card ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(text = "Name: " + card.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Day: " + card.day, fontSize = 16.sp)
                Text(text = "Notes: " + card.notes, fontSize = 16.sp)
            }
        }
    }
}