package com.example.fiicodenou.features.presentation.screens.ContentScreens.WorkoutsScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.presentation.components.BottomBarFitnessApp
import com.example.fiicodenou.features.presentation.viewmodels.WorkoutsViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.myYellow
import io.realm.kotlin.ext.realmListOf

@Composable
fun MainWorkoutScreen(
    navController: NavController,
    workoutsViewModel: WorkoutsViewModel = hiltViewModel()
){
    val workouts by workoutsViewModel.getWorkouts.collectAsState()
    Log.d("workoutsRealm",workouts.toString())

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(
            darkerPurple
        )
    ) {
        HeaderWorkoutScreen(workouts)
        WorkoutScreenMain(navController)
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            BottomBarFitnessApp(navController)
        }
    }
}

//Header
@Composable
fun HeaderWorkoutScreen(
    workouts: List<Workout>
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp),
        colors = CardDefaults.cardColors(
            lighterPurple
        ),
        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp)
            ) {
                Text(text = "My Workouts",
                    style = MaterialTheme.typography.bodyLarge,
                    color = myYellow,
                    fontSize = 35.sp
                )
            }

            LazyRow(modifier = Modifier.padding(top = 30.dp)) {
                items(workouts.size){
                    WorkoutCard(workout = workouts[it])
                }
            }
        }
    }
}

@Composable
fun WorkoutCard(
    workout: Workout
){
    Card(modifier = Modifier
        .height(200.dp)
        .width(240.dp)
        .padding(start = 20.dp, end = 20.dp),
        colors = CardDefaults.cardColors(
            myYellow
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Type: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = workout.type,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "Date: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = workout.date,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "Num. Exercises:  ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = workout.numberOfExercises.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

//Main
@Composable
fun WorkoutScreenMain(
    navController: NavController
){
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
            .padding(25.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth())
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Burnt Calories",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "342" + " KCal",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 17.sp
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Time Trained",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Today",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "2" + " hours",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 17.sp
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Time Trained",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Week",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "8" + " hours",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 17.sp
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "New Skills",
                        style = MaterialTheme.typography.bodyLarge,
                        color = myYellow,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "2",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 17.sp
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, end = 40.dp),
                horizontalArrangement = Arrangement.End
            ) {
                FitnessAppButton(
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp),
                    text = "+",
                    onButClick = {
                        navController.navigate("AddWorkoutScreen")
                                 },
                    color = lighterPurple,
                    textColor = lighterRed
                )
            }
        }
    }
}