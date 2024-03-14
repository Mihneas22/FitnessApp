package com.example.fiicodenou.features.presentation.screens.ContentScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel

@Composable
fun ProfileInfoScreen(
    email: String,
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavController
){
    GoalsScreen(email, userViewModel,navController)
}

@Composable
fun GoalsScreen(
    email: String,
    userViewModel: UserViewModel,
    navController: NavController
){
    var height by remember{
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FitnessAppTextField(text = height,
                onTextChange = {
                    if(it.all {char->
                        char.isDefined()
                    })height=it
                },
                label = "Height",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = weight,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })weight=it
                },
                label = "Weight",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppButton(
                modifier = Modifier.padding(top=30.dp),
                text = "Save Data",
                onButClick = {
                    userViewModel.modifyUserBodyInfo(email,weight,height)
                    navController.navigate("ProfileScreen")
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )

            FitnessAppButton(
                modifier = Modifier.padding(top=30.dp),
                text = "Cancel",
                onButClick = {
                    navController.navigate("ProfileScreen")
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )
        }
    }
}

@Preview
@Composable
fun AccountScreen(

){
    var email by remember{
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            FitnessAppButton(
                modifier = Modifier.padding(top=30.dp),
                text = "Save Data",
                onButClick = {

                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )
        }
    }
}
