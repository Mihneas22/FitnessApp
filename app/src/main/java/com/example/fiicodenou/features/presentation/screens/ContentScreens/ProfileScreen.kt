package com.example.fiicodenou.features.presentation.screens.ContentScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel

@Composable
fun ProfileScreen(
    user: User,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel()
){
    Column(modifier = Modifier.background(Color(0xFF252525))) {
        HeaderProfile(user = user, navController = navController,userViewModel,trackedUserViewModel)
    }
}

@Composable
fun HeaderProfile(
    user: User,
    navController: NavController,
    userViewModel: UserViewModel,
    trackedUserViewModel: TrackedUserViewModel
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xF11FD3C1)
        ),
        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "BackToMenu",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("MainScreen")
                    }
            )

            Icon(imageVector = Icons.Rounded.Close,
                contentDescription = "LogOut",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        trackedUserViewModel.deleteTrackedUser("")
                        userViewModel.logOut()
                        navController.navigate("LoginInScreen")
                    }
            )
        }

        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "AccountIcon",
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                )

                Column(modifier = Modifier
                    .width(300.dp)
                    .height(75.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    user.username?.let {
                        Text(modifier = Modifier
                            .padding(start = 10.dp)
                            .width(300.dp),
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 27.sp
                        )
                    }

                    user.email?.let {
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .width(400.dp),
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            Row(modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                FitnessAppButton(text = "Info",
                    onButClick = {
                        navController.navigate("ProfileInfoScreen")
                    },
                    color = Color(0xFF252525),
                    textColor = Color.White
                )

                FitnessAppButton(text = "Foods",
                    onButClick = {
                        navController.navigate("AddMacrosScreen")
                    },
                    color = Color(0xFF252525),
                    textColor = Color.White
                )

                FitnessAppButton(text = "Workouts",
                    onButClick = {

                    },
                    color = Color(0xFF252525),
                    textColor = Color.White
                )
            }
        }
    }
}