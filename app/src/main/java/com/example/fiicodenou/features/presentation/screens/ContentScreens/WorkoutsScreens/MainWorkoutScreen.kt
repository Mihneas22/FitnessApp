package com.example.fiicodenou.features.presentation.screens.ContentScreens.WorkoutsScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.myYellow

@Composable
fun MainWorkoutScreen(

){

}

@Composable
fun HeaderWorkoutScreen(

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
        }
    }
}
@Preview
@Composable
fun WorkoutScreenMain(

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
        }
    }
}