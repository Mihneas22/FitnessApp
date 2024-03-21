package com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileInfoScreens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun ProfileInfoScreen(
    email: String,
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavController
){
    Column {
        GetStartedScreen()
        GoalsScreen(email, userViewModel,navController)
    }
}

@Composable
fun GoalsScreen(
    email: String,
    userViewModel: UserViewModel,
    navController: NavController
){

    var sex by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    var height by remember{
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(800.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {

                Text(text = "Male",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color(0xF11FD3C1),
                    modifier = Modifier
                        .clickable {
                            sex = "Male"
                        }
                )

                Text(text = "Female",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color(0xF11FD3C1),
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clickable {
                            sex = "Female"
                        }
                )

                Text(text = "Non-Binary",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color(0xF11FD3C1),
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clickable {
                            sex = "Non-Binary"
                        }
                )
            }
            
            Text(modifier = Modifier.padding(top = 10.dp),
                text = sex,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp,
                color = Color.White
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = age,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })age=it
                },
                label = "Age",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = height,
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
                    userViewModel.modifyUserBodyInfo(email,weight,height,sex, age)
                    navController.navigate("ProfileScreen")
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )

            FitnessAppButton(
                modifier = Modifier.padding(top=10.dp),
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

@Composable
fun GetStartedScreen(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
        color = Color(0xFF252525)
    ) {
        val scale = remember {
            androidx.compose.animation.core.Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(3000L)
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Welcome!",
                fontSize = 24.sp,
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xF11FD3C1)
            )
            Text(text = "Let's set your goals",
                fontSize = 24.sp,
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xF11FD3C1)
            )
            Text(text = "and your body details together!",
                fontSize = 24.sp,
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xF11FD3C1)
            )
        }
    }
}