package com.example.andrifitness

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun LogsCreationPage(navController: NavHostController) {

    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val createdLog = createRefFor("createdLog")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(createdLog) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(createdLog.bottom)
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
                    navController.navigate(ApplicationScreens.NewLogsScreen.route)
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
                    fontSize = WButtonFontSizes,
                    color = WButtonContentColor
                )
            }
            Button(
                onClick = {
                    navController.navigate(ApplicationScreens.LogsApplicationScreen.route)
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
                text = "Logs",
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.80f)
                .layoutId("createdLogs")
        ) {
            DisplayCardsLog(navController)
        }
        BottomButtons(navController)
    }
}


@Composable
fun DisplayCardsLog(navController: NavController) {
    val cardsLog = remember { mutableStateOf(CardListLog) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(cardsLog.value) { card ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                backgroundColor = Color.Black,
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Name: " + card.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(text = "Day: " + card.day, fontSize = 16.sp)
                    Text(text = "Notes: " + card.notes, fontSize = 16.sp)
                }
                if (navController.currentDestination?.route == ApplicationScreens.LogsCreationApplicationScreen.route) {
                    val context = LocalContext.current
                    Row(horizontalArrangement = Arrangement.End) {
                        IconButton(
                            onClick = {
                                CardListLog.remove(card)
                                Toast.makeText(context, "Deleted, please click done.", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}