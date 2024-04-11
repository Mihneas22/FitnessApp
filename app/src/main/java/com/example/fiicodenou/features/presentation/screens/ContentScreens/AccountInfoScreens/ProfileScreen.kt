package com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.components.BottomBarFitnessApp
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.WorkoutUserViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.darkerRed
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.mySkinColor
import com.example.fiicodenou.ui.theme.myYellow

@Composable
fun ProfileScreen(
    user: User,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel(),
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    workoutUserViewModel: WorkoutUserViewModel = hiltViewModel()
){
    Column(modifier = Modifier
        .background(darkerPurple)
        .verticalScroll(rememberScrollState())
    ) {
        HeaderProfile(user = user, navController = navController,userViewModel,trackedUserViewModel,trackedFoodViewModel,workoutUserViewModel)
        MainProfileScreen(navController = navController)
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            BottomBarFitnessApp(navController)
        }
    }
}

@Composable
fun HeaderProfile(
    user: User,
    navController: NavController,
    userViewModel: UserViewModel,
    trackedUserViewModel: TrackedUserViewModel,
    trackedFoodViewModel: TrackedFoodViewModel,
    workoutUserViewModel: WorkoutUserViewModel,
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(270.dp),
        colors = CardDefaults.cardColors(
            lighterPurple
        ),
        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = Icons.Rounded.Menu,
                contentDescription = "BackToMenu",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("MainScreen")
                    }
            )

            Icon(imageVector = Icons.Rounded.Logout,
                contentDescription = "LogOut",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        trackedUserViewModel.deleteTrackedUser("")
                        trackedFoodViewModel.deleteAllTrackedFood()
                        workoutUserViewModel.deleteWorkoutUser()
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
                            fontSize = 40.sp,
                            color = lighterRed
                        )
                    }

                    user.email?.let {
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .width(400.dp),
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = mySkinColor
                        )
                    }
                }
            }

            Row(modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                FitnessAppButton(text = "Info",
                    onButClick = {
                        navController.navigate("ProfileInfoScreen")
                    },
                    color = lighterRed,
                    textColor = myYellow
                )

                FitnessAppButton(text = "Foods",
                    onButClick = {
                        navController.navigate("AddMacrosScreen")
                    },
                    color = lighterRed,
                    textColor = myYellow
                )

                FitnessAppButton(text = "Workouts",
                    onButClick = {

                    },
                    color = lighterRed,
                    textColor = myYellow
                )
            }
        }
    }
}

@Composable
fun MainProfileScreen(
    navController: NavController
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            darkerPurple
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 30.dp, end = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("AccountDetailsScreen")
                },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                border = BorderStroke(3.dp, Color.DarkGray)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription = "Account",
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .padding(10.dp)
                    )

                    Text(modifier = Modifier.padding(start = 15.dp),
                        text = "Edit your account information!",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp
                    )
                }
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    //Most Preffered Food Screen
                }
                .padding(top = 40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                border = BorderStroke(3.dp, Color.DarkGray)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Fastfood,
                        contentDescription = "Foods",
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .padding(10.dp)
                    )

                    Text(modifier = Modifier.padding(start = 15.dp),
                        text = "Your Most Used Food!",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp
                    )
                }
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    //Questions Screen
                }
                .padding(top = 40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                border = BorderStroke(3.dp, Color.DarkGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.QuestionMark,
                        contentDescription = "QA",
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .padding(10.dp)
                    )

                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Do You Have Any Questions?",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        //Contact Screen
                    }
                    .padding(top = 40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                border = BorderStroke(3.dp, Color.DarkGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Contact",
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .padding(10.dp)
                    )

                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Do You Wish To Contact Us?",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}