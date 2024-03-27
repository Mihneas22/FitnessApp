package com.example.fiicodenou.features.presentation.screens.ContentScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.R
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.presentation.viewmodels.FoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel


@Composable
fun AddMacrosScreen(
    foodViewModel: FoodViewModel = hiltViewModel(),
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    trackedUserViewModel: TrackedUserViewModel = hiltViewModel(),
    navController: NavController
){
    trackedUserViewModel.getTrackedUser("")
    val resultTrackedUser = trackedUserViewModel.trackedUser
    Log.d("realm","realmData: $resultTrackedUser")

    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        HeaderAddMacrosScreen()
        LowerAddMacrosScreen(foodViewModel,trackedFoodViewModel,navController)
        AddWaterScreen(trackedUserViewModel = trackedUserViewModel,
            trackedUser = resultTrackedUser, navController = navController)
    }
}

@Composable
fun HeaderAddMacrosScreen(){
    Image(painter = painterResource(id = R.drawable.fridgeman),
        contentDescription = "FridgeMan",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun LowerAddMacrosScreen(
    foodViewModel: FoodViewModel,
    trackedFoodViewModel: TrackedFoodViewModel,
    navController: NavController
)
{
    val context = LocalContext.current

    var offset by remember { mutableStateOf(0f) }

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
        .height(680.dp)
        .verticalScroll(rememberScrollState()),
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
            Text(text = "Add Your Personalised Food",
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
                .padding(top = 35.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){

                FitnessAppButton(
                    text = "Save Food",
                    onButClick = {
                        if(name.isNotEmpty() && calories.isNotEmpty() && weight.isNotEmpty()
                            && protein.isNotEmpty() && carbohydrates.isNotEmpty()
                            && fat.isNotEmpty()
                            ){
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
                        }
                        else{
                            Toast.makeText(context,"Enter all values, please!",Toast.LENGTH_SHORT).show()
                        }
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )

                FitnessAppButton(
                    text = "Added Foods",
                    onButClick = {
                        navController.navigate("AddedFoodsScreen")
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )
            }

        }
    }
}

@Composable
fun AddWaterScreen(
    trackedUserViewModel: TrackedUserViewModel,
    trackedUser: TrackedUser,
    navController: NavController
){
    val currentWater = trackedUser.water
    val water = remember{
        mutableDoubleStateOf(0.0)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .verticalScroll(rememberScrollState()),
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
            Text(
                text = "Add Your Water",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color(0xF11FD3C1)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "IconAddWater",
                    modifier = Modifier
                        .height(20.dp).width(20.dp)
                        .background(Color.White)
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "0.1L",
                    onButClick ={
                        water.doubleValue += 0.1
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "0.5L",
                    onButClick = {
                        water.doubleValue += 0.5
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "1L",
                    onButClick = {
                        water.doubleValue += 1.0
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Clear,
                    contentDescription = "IconAddWater",
                    modifier = Modifier
                        .height(20.dp).width(20.dp)
                        .background(Color.White)
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "0.1L",
                    onButClick ={
                        water.doubleValue -= 0.1
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "0.5L",
                    onButClick = {
                        water.doubleValue -= 0.5
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(40.dp)
                        .width(90.dp),
                    text = "1L",
                    onButClick = {
                        water.doubleValue -= 1.0
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.Black
                )
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Current Drank Water:" + " ${
                    water.doubleValue + currentWater!!
                }L",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = Color(0xF11FD3C1)
            )

            FitnessAppButton(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                text = "Cancel",
                onButClick = {
                    navController.navigate("MainScreen")
                    val trackedUserLocal = TrackedUser().apply {
                        this.name = trackedUser.name
                        this.water = trackedUser.water?.plus(water.doubleValue)
                        this.hasExercised = trackedUser.hasExercised
                    }
                    trackedUserViewModel.modifyTrackedUser("",trackedUserLocal)
                },
                color = Color(0xF11FD3C1),
                textColor = Color.Black
            )
        }
    }
}