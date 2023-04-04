package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController

@Composable
fun NewWorkoutLayout(navController: NavHostController) {
    var workoutName by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf("") }
    var workoutNotes by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val newWorkoutInfo = createRefFor("newWorkoutInfo")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(newWorkoutInfo) {
            top.linkTo(topButtons.bottom)
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
                onClick = { navController.navigate(ApplicationScreens.WorkoutCreationApplicationScreen.route)}
            ) {
                Text(
                    text = "X",
                    color = Color.White
                )
            }
            Text(
                text = "New Workout",
                color = Color.White
            )
            Button(
                onClick = {
                    // Passes parameters user added to store new workout in a data structure
                    if (workoutName.isNotEmpty() && selectedDay.isNotEmpty()) {
                        // Function call to a method that takes top level variables then creates a ui with all those parameters
                        // Function then sends created UI back to the previous screen
                        navController.navigate(ApplicationScreens.WorkoutCreationApplicationScreen.route)
                    }
                    else {
                        showDialog = true
                    }
                }
            ) {
                Text(
                    text = "Save",
                    color = Color.White
                )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .layoutId("newWorkoutInfo"),
            verticalArrangement = Arrangement.Top
        ) {
            val days = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            var expanded by remember { mutableStateOf(false) }
            TextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Selected day: $selectedDay", color = Color.White)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .background(Color.LightGray)
            ) {
                Text(
                    text = selectedDay.ifEmpty { "Select a day" }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    days.forEach { day ->
                        DropdownMenuItem(onClick = { selectedDay = day }) {
                            Text(text = day)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = workoutNotes,
                onValueChange = { workoutNotes = it },
                label = { Text("Notes:") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray)
            )
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Fill Parameters") },
                    text = { Text("Please fill out the workout name and day of the workout in order to save it.") },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    }
                )
            }
        }
    }
}