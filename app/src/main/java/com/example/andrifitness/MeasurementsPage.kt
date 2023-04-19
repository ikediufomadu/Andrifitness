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
fun MeasurementsLayout(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Body Measurements") }
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
                    Text(text = "Weight")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Height")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Body Fat")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Body Percentage")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Muscle Mass")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            BottomButtons(navController)
        }

    }

    /*TODO: Add values to button*/

}