package com.example.fiicodenou.features.presentation.screens.ContentScreens

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.User
import java.text.DateFormat
import java.util.Calendar

@Composable
fun MenuScreen(
    user: User,
    navController: NavController
){
    Column {
        user.username?.let { Header(name = it,navController) }
        MenuScreenMainScreen(navController)
    }
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

@Composable
fun MenuScreenMainScreen(
    navController: NavController
){
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)

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
                Text(text = "Date: $dateFormat",
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
                    text = "Today's Stats",
                    onButClick = {
                        navController.navigate("TodayStatsScreen")
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )
            }
        }
    }
}