package com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileInfoScreens

import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodenou.features.domain.models.User_Body
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.mySkinColor
import kotlinx.coroutines.delay

@Composable
fun ChooseFitnessGoal(
    email: String,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    userViewModel.getUserBodyData(email)
    val bodyData = userViewModel.bodyData.value
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .background(darkerPurple),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ChooseFitnessStartScreen()
        WorkoutsScreen(email, userViewModel,bodyData,navController)
    }
}

@Composable
fun ChooseFitnessStartScreen(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        color = darkerPurple
    ) {
        val scale = remember {
            Animatable(0f)
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
            Text(text = "Choose Your Best Suited Workout",
                fontSize = 25.sp,
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.bodyLarge,
                color = lighterRed
            )
            Text(text = "And Your Goal For Fitness!",
                fontSize = 30.sp,
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.bodyLarge,
                color = lighterRed
            )
        }
    }
}

@Composable
fun WorkoutsScreen(
    email: String,
    userViewModel: UserViewModel,
    userBody: User_Body,
    navController: NavController
){
    var myStateMass by remember { mutableStateOf(false) }
    var myStateLoseWeight by remember { mutableStateOf(false) }
    var myStateFit by remember { mutableStateOf(false) }

    var firstOption by remember { mutableStateOf(false) }
    var secondOption by remember { mutableStateOf(false) }
    var thirdOption by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
            colors = CardDefaults.cardColors(
                darkerPurple
            ),
            shape = RectangleShape
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "What Do You Wish To Achieve?",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = myStateMass,
                        onCheckedChange = {
                            myStateMass = it
                            myStateFit = false
                            myStateLoseWeight = false
                        })

                    Text(text = "Gain Muscle Mass",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = myStateLoseWeight,
                        onCheckedChange = {
                            myStateLoseWeight = it
                            myStateFit = false
                            myStateMass = false
                        })

                    Text(text = "Lose Weight",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = myStateFit,
                        onCheckedChange = {
                            myStateFit = it
                            myStateMass = false
                            myStateLoseWeight = false
                        })

                    Text(text = "Stay Fit",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
            colors = CardDefaults.cardColors(
                darkerPurple
            ),
            shape = RectangleShape
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "How Often Could You Workout?",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = lighterRed
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = firstOption,
                        onCheckedChange = {
                            firstOption = it
                            secondOption = false
                            thirdOption = false
                        })

                    Text(text = "1-2 Times/Week",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = secondOption,
                        onCheckedChange = {
                            secondOption = it
                            firstOption = false
                            thirdOption = false
                        })

                    Text(text = "3-4 Times/Week",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = thirdOption,
                        onCheckedChange = {
                            thirdOption = it
                            firstOption = false
                            secondOption = false
                        })

                    Text(text = "4+ Times/Week",
                        style = MaterialTheme.typography.bodyLarge,
                        color = mySkinColor,
                        fontSize = 20.sp
                    )
                }
            }
        }

        val textWorkoutMass = remember{
            mutableStateOf("")
        }

        val textWorkoutDate = remember{
            mutableStateOf("")
        }

        val context = LocalContext.current

        FitnessAppButton(
            modifier = Modifier.padding(bottom = 20.dp),
            text = "Finish",
            onButClick = {
                if(myStateMass)
                {
                    textWorkoutMass.value = "Gain Muscle Mass"
                }else if(myStateLoseWeight)
                {
                    textWorkoutMass.value = "Lose Weight"
                }else{
                    textWorkoutMass.value = "Stay Fit"
                }

                if(firstOption)
                {
                    textWorkoutDate.value = "1/2 times/week"
                }else if(secondOption)
                {
                    textWorkoutDate.value = "3/4 times/week"
                }else{
                    textWorkoutDate.value = "4+ times/week"
                }


                if(textWorkoutMass.value.isNotEmpty() && textWorkoutDate.value.isNotEmpty())
                {
                    userViewModel.modifyUserBodyInfo(
                        email,
                        userBody.weight!!,
                        userBody.height!!,
                        userBody.sex!!,
                        userBody.age!!,
                        textWorkoutMass.value,
                        textWorkoutDate.value
                    )
                    navController.navigate("MainScreen")
                }
                else{
                    Toast.makeText(context,"Enter valid data!", Toast.LENGTH_SHORT).show()
                }
            },
            color = lighterPurple,
            textColor = lighterRed
        )
    }

}

