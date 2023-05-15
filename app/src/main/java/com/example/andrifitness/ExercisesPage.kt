package com.example.andrifitness

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExercisesLayout(navController: NavHostController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val exercises = createRefFor("exercises")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(exercises) {
            top.linkTo(topButtons.bottom)
        }
        constrain(bottomButtons) {
            bottom.linkTo(parent.bottom)
        }
    }
    var selectedCategory by remember { mutableStateOf(ExerciseCategory.UPPER_BODY) }
    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.1f)
                    .layoutId("topButtons")
            ) {
                Button(
                    onClick = { selectedCategory = ExerciseCategory.UPPER_BODY },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedCategory == ExerciseCategory.UPPER_BODY) Color.Gray else Color.LightGray
                    )
                ) {
                    Text(text = "Upper Body")
                }
                Button(
                    onClick = { selectedCategory = ExerciseCategory.LOWER_BODY },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedCategory == ExerciseCategory.LOWER_BODY) Color.Gray else Color.LightGray
                    )
                ) {
                    Text(text = "Lower Body")
                }
                Button(
                    onClick = { selectedCategory = ExerciseCategory.FULL_BODY },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedCategory == ExerciseCategory.FULL_BODY) Color.Gray else Color.LightGray
                    )
                ) {
                    Text(text = "Full Body")
                }
                Button(
                    onClick = { selectedCategory = ExerciseCategory.ABS },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedCategory == ExerciseCategory.ABS) Color.Gray else Color.LightGray
                    )
                ) {
                    Text(text = "Abs")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.9f)
                    .layoutId("exercises")
                    .verticalScroll(rememberScrollState())
            ) {
                ExerciseButton(selectedCategory)
            }
        }
        BottomButtons(navController)
    }
}

data class Exercise(val name: String, val description: String)

@Composable
fun ExerciseButton(category: ExerciseCategory) {
    val exercises = when (category) {
        ExerciseCategory.UPPER_BODY -> listOf(
            Exercise("Bench Press", "3 sets of 10 reps"),
            Exercise("Plank", "60 seconds"),
            Exercise("Push Ups", "3 sets of 10 reps"),
            Exercise("Side Plank","30 seconds each side"),
            Exercise("Lunge Switch Jumps", "10 each side"),
            Exercise("Mountain Climbers", "35 seconds"),
            Exercise("Bicep Curls", "3 sets of 10 reps"),
            Exercise("Burpee", "12 reps"),
            Exercise("Donkey Kick", "8 reps"),
            Exercise("Hold to Push Up", "5 reps")


        )
        ExerciseCategory.LOWER_BODY -> listOf(
            Exercise("Body Weight Squats", "15 reps"),
            Exercise("Body Weight Lunges", "3 sets of 10 each side"),
            Exercise("Body Squat Pulsation", "25 reps"),
            Exercise("Deadlifts", "3 sets of 10 reps"),
            Exercise("Side to Side Squat", "8 each side"),
            Exercise("Squat Jump", "10 reps"),
            Exercise("Mountain Climbers", "30 seconds"),
            Exercise("High Knees", "30 seconds"),
            Exercise("Side to Side Lunge", "8 each side"),
            Exercise("Burpee", "8 reps"),
            Exercise("Squat Jacks","15 reps"),
            Exercise("Pivot Squat", "10 each side"),
            Exercise("Knee to Chest", "8 each side")

        )
        ExerciseCategory.FULL_BODY -> listOf(
            Exercise("Burpees", "3 sets of 10 reps"),
            Exercise("Russian Twists", "3 sets 30 seconds"),
            Exercise("Jumping Jacks", "2 sets of 10 reps"),
            Exercise("Mountain Climbers", "30 each side"),
            Exercise("Knee to Chest", "2 sets 12 each side"),
            Exercise("Plank Elbow to Knee", "10 each side"),
            Exercise("Narrow Push-Up","15 reps"),
            Exercise("Arm Arm Push-Up", "12 reps"),
            Exercise("Plank", "60 seconds")

        )
        ExerciseCategory.ABS -> listOf(
            Exercise("Crunches", "3 sets of 12 reps"),
            Exercise("Side Planks", "30 seconds each side"),
            Exercise("Russian Twists", "3 sets of 10 reps"),
            Exercise("Kick-Ups","3 sets 15 reps"),
            Exercise("Mountain Climbers", "5 sets 30 seconds"),
            Exercise("Leg Raise to Kick-Up", "10 reps"),
            Exercise("Flutter Kicks", "10 each side"),
            Exercise("Around the Worlds", "5 each direction")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        exercises.forEach { exercise ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.5.dp)
            ){
                    Text(text = exercise.name, color = Color.White)
                    Text(text = exercise.description, color = Color.White)

            }
            Divider(color = Color.White)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {
            val uriHandler = LocalUriHandler.current
            Button(
                onClick = {
                    if (ExerciseCategory.values().any{it.title =="Upper Body" }){
                        uriHandler.openUri("https://youtube.com/clip/UgkxR0UPw5BfhtOWzHyywYtoaOD_ZcdoAd1I")
                    }else if (ExerciseCategory.values().any{it.title =="Lower Body" }){
                        uriHandler.openUri("https://youtube.com/clip/UgkxR0UPw5BfhtOWzHyywYtoaOD_ZcdoAd1I")
                    }else if (ExerciseCategory.values().any{it.title =="Full Body" }){
                    uriHandler.openUri("https://youtube.com/clip/UgkxmyrPhmXQt4GpsJRRDgrwMH_lMa2iLk0j")
                    }else if (ExerciseCategory.values().any{it.title =="Abs" }){
                        uriHandler.openUri("https://youtube.com/clip/Ugkxb0tboxZVnBIoqPq6rpAS00x5Sl3mJb-7")
                    }else{
                        println("Empty Video")
                    }
                }
            ) {
                Text(text = "Video")
            }
        }
    }
}

enum class ExerciseCategory (val title: String) {
    UPPER_BODY("Upper Body"),
    LOWER_BODY("Lower Body"),
    FULL_BODY("Full Body"),
    ABS("Abs")
}


