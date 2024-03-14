package com.example.fiicodenou.features.presentation.screens.ContentScreens

import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.User

@Composable
fun MenuScreen(
    user: User,
    navController: NavController
){
    user.username?.let { Header(name = it,navController) }
}

@Composable
fun Header(
    name: String,
    navController: NavController
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Welcome,",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 30.sp
                )

                Icon(modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .clickable {
                        navController.navigate("ProfileScreen")
                    },
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Account")
            }

            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Hi,",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp,
                    color = Color(0xF11FD3C1),
                    modifier = Modifier.padding(start = 40.dp,top=13.dp)
                )

                Text(text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp,
                    color = Color(0xF1FFFFFF),
                    modifier = Modifier.padding(start = 7.dp,top=13.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenMainScreen(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "Date: 11.03.2024",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp,
                    color = Color(0xF11FD3C1)
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                FitnessAppButton(
                    modifier = Modifier.padding(end = 30.dp),
                    text = "Macros For Today",
                    onButClick = {
                                 //navController.navigate("AddMacrosScreen")
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )
            }
        }
    }
}