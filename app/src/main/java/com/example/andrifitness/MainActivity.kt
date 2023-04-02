package com.example.andrifitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.andrifitness.ui.theme.AndriFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AndriFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Layout(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Layout(navController: NavController) {
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
            Text(
                text = "Icon1",
                color = Color.White,
            )
            Text(
                text = "Icon2",
                color = Color.White
            )
            Text(
                text = "Icon3",
                color = Color.White
            )
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
        Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f)
                    .layoutId("bottomButtons"),
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        Text(text = "workouts", color = Color.White)
        Text(text = "Exercises", color = Color.White)
        Text(text = "Logs", color = Color.White)
        Text(text = "Measurements", color = Color.White)
        Text(text = "Settings", color = Color.White)
        Button(onClick = { navController.navigate(Screen.WorkoutCreationScreen.route) }) {
            
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndriFitnessTheme {
        val navController = rememberNavController()
        Layout(navController = navController)
    }
}