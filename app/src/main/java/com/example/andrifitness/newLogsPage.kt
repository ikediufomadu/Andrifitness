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
fun NewLogsPage(navController: NavHostController) {
    var logName by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf("") }
    var logNotes by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var createCard by remember { mutableStateOf(false) }

    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val newLogsInfo = createRefFor("newLogsInfo")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(newLogsInfo) {
            top.linkTo(topButtons.bottom)
        }
    }
    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
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
                    navController.navigate(ApplicationScreens.LogsCreationApplicationScreen.route)
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
                    text = "X",
                    fontSize = WButtonFontSizes,
                    color = WButtonContentColor
                )
            }
            Text(
                text = "New Log",
                color = Color.White
            )
            Button(
                onClick = {
                    // Passes parameters user added to store new workout in a data structure
                    if (logName.isNotEmpty() && selectedDay.isNotEmpty()) {
                        createCard = true
                    }
                    else {
                        showDialog = true
                    }
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
                    text = "Save",
                    fontSize = WButtonFontSizes,
                    color = Color.LightGray,
                )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .layoutId("newLogsInfo"),
            verticalArrangement = Arrangement.Top
        ) {
            val days = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            var expanded by remember { mutableStateOf(false) }
            TextField(
                value = logName,
                onValueChange = { logName = it },
                label = { Text("What did you do?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Select a day: $selectedDay", color = Color.White)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .background(Color.LightGray)
                    .padding(top = 24.dp),
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
                        DropdownMenuItem(onClick = {
                            selectedDay = day
                            expanded = false}
                        ) {
                            Text(text = day)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = logNotes,
                onValueChange = { logNotes = it },
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
            if (createCard) {
                createCard = false
                BuildCardLog(logTitle = logName, day = selectedDay, notes = logNotes)
                navController.navigate(ApplicationScreens.LogsCreationApplicationScreen.route)
            }
        }
    }
}
data class CardDataLog(
    val logTitle: String,
    val day: String,
    val notes: String
)

val CardListLog = mutableListOf<CardDataLog>()
@Composable
fun AddCardToListLog(cardDataLog: CardDataLog) {
    CardListLog.add(cardDataLog)
}
@Composable
fun BuildCardLog(logTitle: String, day: String, notes: String = "") {
    val card = CardDataLog (logTitle = logTitle, day = day, notes = notes)
    AddCardToListLog(cardDataLog = card)
}