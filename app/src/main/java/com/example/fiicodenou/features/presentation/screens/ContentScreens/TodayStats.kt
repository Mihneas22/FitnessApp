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
import androidx.compose.runtime.mutableDoubleStateOf
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
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.NutritionalNecesity
import com.example.fiicodenou.features.presentation.components.BottomBarFitnessApp
import com.example.fiicodenou.features.presentation.screens.components.PieChart
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.mySkinColor
import com.example.fiicodenou.ui.theme.myYellow
import io.realm.kotlin.ext.realmListOf
import kotlin.math.roundToInt

@Composable
fun TodayStatsScreen(
    navController: NavController,
    email: String,
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    userViewModel.getUserBodyData(email)
    val bodyData = userViewModel.bodyData.value
    val calculator = remember {
        mutableDoubleStateOf(0.0)
    }
    val calculatorProtein = remember {
        mutableDoubleStateOf(0.0)
    }

    val calculatorCarbs = remember {
        mutableDoubleStateOf(0.0)
    }

    val calculatorFat = remember {
        mutableDoubleStateOf(0.0)
    }

    if(bodyData.workoutDate?.isEmpty()!!)
    {
        calculator.doubleValue = 0.0
        calculatorProtein.doubleValue = 0.0
        calculatorCarbs.doubleValue = 0.0
        calculatorFat.doubleValue = 0.0
    }else{
        if (bodyData.sex == "Male") {
            calculator.doubleValue =
                13.397 * bodyData.weight?.toIntOrNull()!! + 4.799 * bodyData.height?.toIntOrNull()!! - 5.677 * bodyData.age?.toIntOrNull()!! + 88.362
        } else if (bodyData.sex == "Female") {
            calculator.doubleValue =
                9.247 * bodyData.weight?.toIntOrNull()!! + 3.098 * bodyData.height?.toIntOrNull()!! - 4.330 * bodyData.age?.toIntOrNull()!! + 447.593
        } else if(bodyData.sex == "Non-Binary"){
            calculator.doubleValue =
                9.247 * bodyData.weight?.toIntOrNull()!! + 3.098 * bodyData.height?.toIntOrNull()!! - 4.330 * bodyData.age?.toIntOrNull()!! + 447.593
        }


        if (bodyData.workoutDate == "4+ times/week") {
            calculator.doubleValue *= 1.725
            calculatorProtein.doubleValue = bodyData.weight?.toDoubleOrNull()!! * 2.5
        } else if (bodyData.workoutDate == "3/4 times/week") {
            calculator.doubleValue *= 1.55
            calculatorProtein.doubleValue = bodyData.weight?.toDoubleOrNull()!! * 2
        } else if(bodyData.workoutDate == "1/2 times/week") {
            calculator.doubleValue *= 1.375
            calculatorProtein.doubleValue = bodyData.weight?.toDoubleOrNull()!! * 1.5
        }


        if (bodyData.workoutPlan == "Gain Muscle Mass") {
            calculator.doubleValue += 300
        } else if (bodyData.workoutPlan == "Lose Weight") {
            calculator.doubleValue -= 500
        }

        calculatorCarbs.doubleValue = (calculator.doubleValue * 0.50)/4
        calculatorFat.doubleValue = (calculator.doubleValue * 0.30)/9
    }

    val nutriData = NutritionalNecesity(
        calculator.doubleValue,
        calculatorProtein.doubleValue,
        calculatorCarbs.doubleValue,
        calculatorFat.doubleValue
    )

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        HeaderStats(trackedFoodViewModel = trackedFoodViewModel, trackedUserViewModel = trackedUserViewModel, nutriData = nutriData)
        SportsStats(navController = navController)
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            BottomBarFitnessApp(navController)
        }
    }
}

@Composable
fun HeaderStats(
    trackedFoodViewModel: TrackedFoodViewModel,
    trackedUserViewModel: TrackedUserViewModel,
    nutriData: NutritionalNecesity
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
            containerColor = darkerPurple
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
                    color = myYellow
                )
                Text(text = caloriesValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
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
                    color = myYellow
                )
                Text(text = hasExercisedResponse.value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
                    fontSize = 17.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Remaining Calories",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow
                )
                Text(text = "${(nutriData.calories-caloriesValuesFull).roundToInt()}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Water",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow
                )
                Text(text = "${trackedUserWater}L",
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
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
                    Text(text = "${(nutriData.protein-proteinValuesFull).roundToInt()}g"+" Remained Protein",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(modifier = Modifier.padding(top = 60.dp),
                        text = "${(nutriData.carbs-carboValuesFull).roundToInt()}g"+" Remained Carbs",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(modifier = Modifier.padding(top = 60.dp),
                        text = "${(nutriData.fat-fatValuesFull).roundToInt()}g"+" remained Fat",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Protein",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow
                )
                Text(text = proteinValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Carbs",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow,
                )
                Text(text = carboValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
                    fontSize = 20.sp
                )

                Text(modifier = Modifier.padding(top = 40.dp),
                    text = "Fat",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow
                )
                Text(text = fatValuesFull.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed,
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
            darkerPurple
        ),
        shape = RectangleShape
    ){
        Column(modifier = Modifier.padding(top=25.dp)) {
            Text(text = "Workout Completed Today",
                modifier = Modifier.padding(start = 25.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp,
                color = lighterRed
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
        }
    }
}

