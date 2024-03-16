package com.example.fiicodenou.features.presentation.screens.ContentScreens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.R
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.presentation.viewmodels.FoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.realm.kotlin.ext.realmListOf
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AddedFoodsScreen(
    foodViewModel: FoodViewModel = hiltViewModel(),
    trackedFoodViewModel: TrackedFoodViewModel = hiltViewModel(),
    navController: NavController
){
    val resultTrackedFoods by trackedFoodViewModel.getAllTrackedFood.collectAsState(initial = realmListOf())
    trackedFoodViewModel.calculateAllCalories(resultTrackedFoods)
    val caloriesValuesFull = trackedFoodViewModel.caloriesFull.doubleValue
    val proteinValuesFull = trackedFoodViewModel.proteinFull.doubleValue
    val carboValuesFull = trackedFoodViewModel.carbohydratesFull.doubleValue
    val fatValuesFull = trackedFoodViewModel.fatFull.doubleValue
    Log.d("valueCalories","$caloriesValuesFull")

    //Firebase
    foodViewModel.getFood()
    val resultFirebase = foodViewModel.result.value
    foodViewModel.getApprovedFood(resultFirebase)
    val approvedList = foodViewModel.resultApprovedList.value
    //Firebase

    Column(modifier = Modifier.
            fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderAddedFoodsScreen()
        ImplementSearchBar(foodViewModel,approvedList, navController = navController, trackedFoodViewModel)
        ShowAddedFoods(trackedFoodViewModel = trackedFoodViewModel, foods = resultTrackedFoods)
    }

}

@Composable
fun HeaderAddedFoodsScreen(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Image(painter = painterResource(id = R.drawable.forgemacros),
            contentDescription = "EatingMan",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 3.dp,
            color = Color.Gray
        )
    }
}

@Composable
fun ImplementSearchBar(
    foodViewModel: FoodViewModel,
    foodListReal: List<Food>,
    navController: NavController,
    trackedFoodViewModel: TrackedFoodViewModel
){
    val foodName = remember{
        mutableStateOf("")
    }

    val listChange = remember {
        mutableStateOf<List<Food>>(emptyList())
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(Color(0xFF252525)),
        horizontalArrangement = Arrangement.End,
    ) {
        Icon(imageVector = Icons.Default.Close,
            contentDescription = "Go Back",
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .clickable {
                    navController.navigate("ProfileScreen")
                }
        )
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            FitnessAppTextField(text = foodName.value,
                onTextChange = {
                    if(it.all {char->
                        char.isLetter()
                    })foodName.value=it
                },
                label = "Search For Products",
                color = Color(0xF11FD3C1),//0xFF252525
                textColor = Color(0xFF252525),
                modifier = Modifier.padding(top=30.dp)
            )

            FitnessAppButton(
                modifier = Modifier.padding(top = 10.dp),
                text = "Get Results",
                onButClick = {
                    foodViewModel.getFoodsRelatedToName(foodName.value,foodListReal)
                    listChange.value = foodViewModel.resultRelatedToNameList.value
                    Log.d("dataSearch","search: ${foodViewModel.resultRelatedToNameList.value}")
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White
            )

            GetSearchBarResults(trackedFoodViewModel = trackedFoodViewModel,listChange.value)
        }
    }
}

@Composable
fun GetSearchBarResults(
    trackedFoodViewModel: TrackedFoodViewModel,
    foods: List<Food>
){
    Card(modifier = Modifier
        .height(250.dp)
        .width(250.dp)
        .padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xF11FD3C1)
        ),
        shape = RectangleShape
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(foods){food->
                FoodBarResults(trackedFoodViewModel = trackedFoodViewModel,food = food)
            }
        }
    }
}

@Composable
fun FoodBarResults(
    trackedFoodViewModel: TrackedFoodViewModel,
    food: Food
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(top = 15.dp)
        .clickable {
            val trackedFood = TrackedFood().apply {
                this.name = food.name
                this.weight = food.weight
                this.calories = food.calories
                this.protein = food.protein
                this.carbohydrates = food.carbohydrates
                this.fat = food.fat
            }
                   trackedFoodViewModel.addTrackedFood(trackedFood)
        },
        border = BorderStroke(3.dp,Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        )
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(70.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = food.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1)
                )
            }

            VerticalDivider(modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp)
            )

            Column(modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight()
                .width(100.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = food.protein + "g Protein",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 13.sp
                )

                Text(
                    text = food.carbohydrates + "g Carbs",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 13.sp
                )

                Text(
                    text = food.fat + "g Fat",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 13.sp
                )
            }

            VerticalDivider(modifier = Modifier
                .fillMaxHeight()
            )

            Column(modifier = Modifier
                .fillMaxHeight()
                .width(100.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = food.calories + "Calories",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 7.sp
                )

                Text(text = food.weight + "Weight",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xF11FD3C1),
                    fontSize = 7.sp
                )
            }
        }
    }
}

//Showing food that we added


@Composable
fun ShowAddedFoods(
    trackedFoodViewModel: TrackedFoodViewModel,
    foods: List<TrackedFood>
){
    val viewModel = viewModel<FoodViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(600.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        SwipeRefresh(state = swipeRefreshState,
            onRefresh = viewModel::loadFood,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = Color(0xF11FD3C1),
                    contentColor = Color.Gray
                )
            }
        ) {

            LazyRow(modifier = Modifier
                .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(foods){food->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FoodCardFront(food = food)
                        Text(text = "Delete",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xF11FD3C1),
                            modifier = Modifier.clickable {
                                trackedFoodViewModel.deleteTrackedFood(food.name)
                                Log.d("dataSize","size: ${food.name}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FoodCardFront(
    food: TrackedFood
){
    Card(modifier = Modifier
        .width(150.dp)
        .height(170.dp)
        .padding(top = 3.dp, end = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xF11FD3C1)
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Name: " +food.name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Weight: " + food.weight,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Calories: " + food.calories,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Protein: " + food.protein,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Carbohydrates: " + food.carbohydrates,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )

            Text(modifier = Modifier.padding(top = 10.dp),
                text = "Fat: " + food.fat,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 13.sp,
                color = Color.Black
            )
        }
    }
}