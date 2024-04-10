package com.example.fiicodenou.features.presentation.components

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed

@Composable
fun BottomBarFitnessApp(
    navController: NavController
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp),
        colors = CardDefaults.cardColors(
            lighterPurple
        ),
        shape = RectangleShape
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate("MainScreen")
                }
            ) {
                Icon(imageVector = Icons.Default.Home,
                    contentDescription = "HomeScreen",
                    modifier = Modifier.width(40.dp).height(40.dp)
                )

                Text(text = "Home",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = lighterRed
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate("AddMacrosScreen")
                }
            ) {
                Icon(imageVector = Icons.Default.FoodBank,
                    contentDescription = "FoodsScreen",
                    modifier = Modifier.width(40.dp).height(40.dp)
                )

                Text(text = "My Foods",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = lighterRed
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    //WorkoutsScreen
                }
            ) {
                Icon(imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "WorkoutsScreen",
                    modifier = Modifier.width(40.dp).height(40.dp)
                )

                Text(text = "My Workouts",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = lighterRed
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate("ProfileScreen")
                }
            ) {
                Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription = "AccountScreen",
                    modifier = Modifier.width(40.dp).height(40.dp)
                )

                Text(text = "Account",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = lighterRed
                )
            }
        }
    }
}