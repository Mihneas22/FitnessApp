package com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AccountCircle
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AccountDetailsScreen(
    name: String,
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF252525))) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(modifier = Modifier
                .padding(start = 5.dp, top = 5.dp)
                .clickable {
                    navController.navigate("ProfileScreen")
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go Back",
                tint = Color.White
            )

            Text(modifier = Modifier.padding(start = 100.dp),
                text = "My Fitness App",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 20.sp
            )
        }
        HeaderAccountDetails()
        MainAccountDetails(name,navController)
    }
}
@Composable
fun HeaderAccountDetails(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Manage Your Account",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 25.sp
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Manage your account details and information",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Text(text = "and settings to ensure the best experience using the app!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MainAccountDetails(
    name: String,
    navController: NavController
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "My AccountImg",
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
            Text(text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(text = "My Fitness App",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color.Gray
            )

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            navController.navigate("EditYourNameScreen")
                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Name",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = Color.White
                        )

                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            navController.navigate("EditYourPasswordScreen")
                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Password",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = Color.White
                        )

                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {

                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Profile Picture",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = Color.White
                        )

                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {

                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Email",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            color = Color.White
                        )

                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}