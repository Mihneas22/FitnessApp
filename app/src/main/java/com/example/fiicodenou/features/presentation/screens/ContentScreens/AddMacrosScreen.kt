package com.example.fiicodenou.features.presentation.screens.ContentScreens

import android.util.Log
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.presentation.viewmodels.FoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.realm.kotlin.ext.realmListOf


@Composable
fun AddMacrosScreen(
    foodViewModel: FoodViewModel = hiltViewModel(),
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    navController: NavController
){
    foodViewModel.getFood()
    val resultFirebase = foodViewModel.result.value

    val resultTrackedFoods by trackedFoodViewModel.getAllTrackedFood.collectAsState(initial = realmListOf())
    Log.d("realm","realmData: ${resultTrackedFoods.size}")

    Column {
        HeaderAddMacrosScreen(foodViewModel,trackedFoodViewModel,navController)
        ShowAddedFoods(trackedFoodViewModel = trackedFoodViewModel,foods = resultTrackedFoods)
    }
}


@Composable
fun HeaderAddMacrosScreen(
    foodViewModel: FoodViewModel,
    trackedFoodViewModel: TrackedFoodViewModel,
    navController: NavController
)   {

    var name by remember {
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    var calories by remember {
        mutableStateOf("")
    }

    var protein by remember {
        mutableStateOf("")
    }

    var carbohydrates by remember {
        mutableStateOf("")
    }

    var fat by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(650.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 30.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Add Your Food",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = name,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })name=it
                },
                label = "Add A Name",
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
                label = "Add Weight (g)",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = calories,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })calories=it
                },
                label = "Add Calories (KCal)",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = protein,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })protein=it
                },
                label = "Add Protein (g)",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = carbohydrates,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })carbohydrates=it
                },
                label = "Add Carbohydrates (g)",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top=20.dp),
                text = fat,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })fat=it
                },
                label = "Add fat (g)",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){

                FitnessAppButton(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Cancel",
                    onButClick = {
                        navController.navigate("MainScreen")
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp, end = 15.dp),
                    text = "Save Food",
                    onButClick = {
                        val food = Food(
                            name = name,
                            calories = calories,
                            weight = weight,
                            protein = protein,
                            carbohydrates = carbohydrates,
                            fat = fat,
                            approved = false
                        )
                        val trackedFood = TrackedFood().apply {
                            this.name = name
                            this.weight = weight
                            this.calories = calories
                            this.protein = protein
                            this.carbohydrates = carbohydrates
                            this.fat = fat
                        }

                        foodViewModel.createFood(food)
                        trackedFoodViewModel.addTrackedFood(trackedFood)
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )
            }

            FitnessAppButton(
                modifier = Modifier.padding(start = 110.dp),
                text = "Delete All",
                onButClick = {
                    trackedFoodViewModel.deleteAllTrackedFood()
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )

            FitnessAppButton(
                modifier = Modifier.padding(start = 110.dp),
                text = "Travel to Added Foods",
                onButClick = {
                    navController.navigate("AddedFoodsScreen")
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )

        }
    }
}