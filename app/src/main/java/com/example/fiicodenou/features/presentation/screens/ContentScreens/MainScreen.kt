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

@Composable
fun PieChar(
    data: Map<String,Int>,
    radiusOuter: Dp = 90.dp,
    charBaeWidth: Dp = 20.dp,
    animDuration: Int = 1000
){
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    // To set the value of each Arc according to
    // the value given in the data, we have used a simple formula.
    // For a detailed explanation check out the Medium Article.
    // The link is in the about section and readme file of this GitHub Repository
    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    // add the colors as per the number of data(no. of pie chart entries)
    // so that each data will get a color
    val colors = listOf(
        Blue
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    // it is the diameter value of the Pie
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // to play the animation only once when the function is Created or Recomposed
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
}