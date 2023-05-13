package com.example.andrifitness

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExercisesLayout(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exercises") }
            )
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Shoulders")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Chest")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Back")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Arms")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Abdomen")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Legs")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Cardio")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Glutes")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            BottomButtons(navController)
        }

    }

    /*TODO: Add values to button*/
}