package com.example.fiicodenou.features.presentation.screens.ContentScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodenou.features.presentation.screens.components.PieChart
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import io.realm.kotlin.ext.realmListOf

@Composable
fun TodayStatsScreen(
    navController: NavController,
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel()){
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        HeaderStats(trackedFoodViewModel = trackedFoodViewModel, trackedUserViewModel = trackedUserViewModel)
        SportsStats(navController = navController)
    }
}

@Composable
fun HeaderStats(
    trackedFoodViewModel: TrackedFoodViewModel,
    trackedUserViewModel: TrackedUserViewModel
){
    val resultTrackedFoods by trackedFoodViewModel.getAllTrackedFood.collectAsState(initial = realmListOf())
    trackedFoodViewModel.calculateAllCalories(resultTrackedFoods)
    trackedFoodViewModel.calculateAllProtein(resultTrackedFoods)
    trackedFoodViewModel.calculateAllCarbohydrates(resultTrackedFoods)
    trackedFoodViewModel.calculateAllFat(resultTrackedFoods)
    val caloriesValuesFull = trackedFoodViewModel.caloriesFull.doubleValue
    val proteinValuesFull = trackedFoodViewModel.proteinFull.doubleValue
    val carboValuesFull = trackedFoodViewModel.carbohydratesFull.doubleValue
    val fatValuesFull = trackedFoodViewModel.fatFull.doubleValue

    trackedUserViewModel.getTrackedUser("")
    val trackedUserWater = trackedUserViewModel.trackedUser.water
    val trackedUserExercise = trackedUserViewModel.trackedUser.hasExercised

    val hasExercisedResponse = remember{
        mutableStateOf("")
    }

    if(trackedUserExercise == false){
        hasExercisedResponse.value = "Did Not Work Out"
    }else{
        hasExercisedResponse.value = "Worked Out"
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(550.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Calorie Budget",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = caloriesValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        ) {
            Column(modifier = Modifier
                .padding(start = 25.dp,top=15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Exercise",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = hasExercisedResponse.value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 17.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Steps",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = "8k",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Water",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = "${trackedUserWater}L",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )
            }

            Column(modifier = Modifier
                .padding(start = 25.dp, top = 15.dp)
                .width(240.dp)
                .height(225.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                PieChart(data = mapOf(
                    Pair("Protein", proteinValuesFull),
                    Pair("Carbs", carboValuesFull),
                    Pair("Fat", fatValuesFull),
                ))
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(end = 25.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(modifier = Modifier
                .height(400.dp)
                .padding(end = 100.dp, top = 25.dp)
            ) {
                Column {
                    Text(text = "30g remained Protein",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )

                    Text(modifier = Modifier.padding(top = 60.dp),
                        text = "74g Remained Carbs",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )

                    Text(modifier = Modifier.padding(top = 60.dp),
                        text = "20g remained Fat",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Protein",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = proteinValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Carbs",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                )
                Text(text = carboValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Fat",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(text = fatValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun SportsStats(
    navController: NavController
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        Column(modifier = Modifier.padding(top=25.dp)) {
            Text(text = "Workout Completed Today",
                modifier = Modifier.padding(start = 25.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp,//0xF11FD3C1
                color = Color(0xF11FD3C1)
            )

            Card(modifier = Modifier
                .height(200.dp)
                .width(250.dp)
                .padding(top = 25.dp, start = 25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                )
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "Coming Soon...",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp)
                }
            }

            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                Icon(imageVector = Icons.Outlined.Close,
                    contentDescription = "Cancel",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(50.dp)
                        .background(Color.White)
                        .clickable {
                            navController.navigate("ProfileScreen")
                        }
                )
            }
        }
    }
}

