package com.example.andrifitness

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController


data class UserProfile(
    val name: String,
    val age: Int,
    val height: Float,
    val weight: Float
)

class UserProfileViewModel : ViewModel() {
    val userProfile = mutableStateOf<UserProfile?>(null)

    fun saveUserProfile(profile: UserProfile) {
        userProfile.value = profile
    }
}

@Composable
fun UserProfileLayout(navController: NavHostController, userProfileViewModel: UserProfileViewModel) {
    val userProfile = userProfileViewModel.userProfile.value

    Column {
        Text(text = "User Profile", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        if (userProfile != null) {
            Text(text = "Name: ${userProfile.name}")
            Text(text = "Age: ${userProfile.age}")
            Text(text = "Height: ${userProfile.height} cm")
            Text(text = "Weight: ${userProfile.weight} kg")
        } else {
            Text(text = "No user profile found.")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(ApplicationScreens.UserProfileForm.route) }) {
            Text(text = "Edit User Profile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}
@Composable
fun UserProfileForm(
    navController: NavHostController,
    userProfileViewModel: UserProfileViewModel
) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }

    Column {
        Text(text = "User Profile", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Name") }
        )

        OutlinedTextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text(text = "Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = height.value,
            onValueChange = { height.value = it },
            label = { Text(text = "Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = weight.value,
            onValueChange = { weight.value = it },
            label = { Text(text = "Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val userProfile = UserProfile(
                name = name.value,
                age = age.value.toIntOrNull() ?: 0,
                height = height.value.toFloatOrNull() ?: 0f,
                weight = weight.value.toFloatOrNull() ?: 0f
            )
            userProfileViewModel.saveUserProfile(userProfile)
            navController.popBackStack()
        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}
