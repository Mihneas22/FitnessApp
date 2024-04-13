package com.example.fiicodenou.features.presentation.screens.ContentScreens

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.components.BottomBarFitnessApp
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.myYellow
import java.text.DateFormat
import java.util.Calendar

@Composable
fun MenuScreen(
    user: User,
    navController: NavController,
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel()
){
    val calendar = Calendar.getInstance().time
    val timer = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar)
    trackedFoodViewModel.getLocalDate()
    if(timer != trackedFoodViewModel.localDate.value)
    {
        trackedFoodViewModel.updateLocalDate(timer)
        trackedFoodViewModel.deleteAllTrackedFood()
        trackedUserViewModel.modifyTrackedUser("", TrackedUser())
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            darkerPurple
        )
    ) {
        user.username?.let { Header(name = it) }
        MenuScreenMainScreen(navController)
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            BottomBarFitnessApp(navController)
        }
    }
}

@Composable
fun Header(
    name: String
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = lighterPurple
        ),
        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
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
                    color = lighterRed,
                    fontSize = 35.sp
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Hi,",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 40.sp,
                    color = lighterRed,
                    modifier = Modifier.padding(start = 40.dp,top=13.dp)
                )

                Text(text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 40.sp,
                    color = myYellow,
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
            darkerPurple
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
                    color = lighterRed
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
                    color = lighterPurple,
                    textColor = lighterRed
                )
            }
        }
    }
}