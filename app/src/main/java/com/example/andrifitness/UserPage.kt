package com.example.andrifitness

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController


data class UserProfile(
    val name: String,
    val age: Int,
    val gender: String,
    val height: Float,
    val weight: Float
)

class UserProfileViewModel : ViewModel() {
    var userProfile: MutableLiveData<UserProfile?> = MutableLiveData(null)
}



@Composable
fun UserProfile(
    navController: NavHostController
) {
  //  val userProfile = viewModel.userProfile ?: UserProfile()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}