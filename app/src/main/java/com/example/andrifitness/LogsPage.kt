package com.example.andrifitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController

@Composable
fun LogsPage(navController: NavController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val logs = createRefFor("Logs")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(logs) {
            top.linkTo(topButtons.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(logs.bottom)
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
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { navController.navigate(ApplicationScreens.LogsCreationApplicationScreen.route)},
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
                .layoutId("Logs")
        ) {
            DisplayCards(navController)
        }

        BottomButtons(navController)
    }
}
