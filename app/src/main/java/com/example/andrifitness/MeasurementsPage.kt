package com.example.andrifitness

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeasurementsLayout(navController: NavHostController, viewModel: MeasurementViewModel) {
    val scaffoldState = rememberScaffoldState()
    val weight = remember { mutableStateOf("") }
    val bodyFat = remember { mutableStateOf("") }
    val muscleMass = remember { mutableStateOf("") }
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val workouts = createRefFor("workouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(workouts) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(workouts.bottom)
        }
    }

    ConstraintLayout(

        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column() {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color.White)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = weight.value,
                label = { Text("Weight (in kg)", color = Color.Black) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { weight.value = it }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = bodyFat.value,
                label = { Text("Body Fat Percentage", color = Color.Black) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { bodyFat.value = it }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = muscleMass.value,
                label = { Text("Muscle Mass (in kg)", color = Color.Black) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = { muscleMass.value = it }
            )
            Button(
                    onClick = {
                        viewModel.addMeasurement(
                            weight.value.toFloatOrNull(),
                            bodyFat.value.toFloatOrNull(),
                            muscleMass.value.toFloatOrNull()
                        )
                    },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            enabled = weight.value.isNotEmpty() && bodyFat.value.isNotEmpty() && muscleMass.value.isNotEmpty()
            ) {
            Text(text = "Add Measurement", color = Color.White)
        }

        }
        BottomButtons(navController)
        }
    }

}

@Composable
fun MeasurementHistory(navController: NavHostController, viewModel: MeasurementViewModel) {
    val measurements = viewModel.measurementList.value ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Measurement History",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (measurements.isEmpty()) {
            Text("No measurements yet.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(measurements) { index, measurement ->
                    Text(
                        text = "${index + 1} Height=${measurement.height}cm, Weight=${measurement.weight}kg, Body Fat=${measurement.bodyFat}%, Muscle Mass=${measurement.muscleMass}%",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
        Button(
            onClick = { navController.popBackStack()},
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Back to Profile")
        }
        BottomButtons(navController)
    }
}

data class Measurement(
    val height: Float,
    val weight: Float,
    val bodyFat: Float,
    val muscleMass: Float
)

class MeasurementViewModel : ViewModel() {
    var measurementList = MutableLiveData(mutableListOf<Measurement>())

    fun addMeasurement(height: Float?, weight: Float?, bodyFat: Float?, muscleMass: Float? = 0f) {
        height?.let { h ->
            weight?.let { w ->
                bodyFat?.let { bf ->
                    muscleMass?.let { mm ->
                        val measurements = measurementList.value ?: mutableListOf()
                        val newMeasurement = Measurement(
                            height = h,
                            weight = w,
                            bodyFat = bf,
                            muscleMass = mm
                        )
                        measurements.add(newMeasurement)
                        measurementList.value = measurements
                    }
                }
            }
        }
    }

}
