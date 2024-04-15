package com.example.fiicodenou

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.screens.AuthScreens.LoginInScreen
import com.example.fiicodenou.features.presentation.screens.AuthScreens.SignUpScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens.AccountDetailsScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens.DeleteAccountScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens.EditYourNameScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens.EditYourPasswordScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens.ProfileScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AddMacrosScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AddedFoodsScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.MenuScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileInfoScreens.ChooseFitnessGoal
import com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileInfoScreens.ProfileInfoScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.TodayStatsScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.WorkoutsScreens.AddWorkoutScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.WorkoutsScreens.MainWorkoutScreen
import com.example.fiicodenou.features.presentation.viewmodels.AmericanFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.MainViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedFoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.TrackedUserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import com.example.fiicodenou.features.presentation.viewmodels.WorkoutUserViewModel
import com.example.fiicodenou.ui.theme.FiiCodeNouTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val userDataViewModel by viewModels<UserViewModel>()
    private val trackedUser by viewModels<TrackedUserViewModel>()
    private val trackedFoodViewModel by viewModels<TrackedFoodViewModel>()
    private val workoutUserViewModel by viewModels<WorkoutUserViewModel>()
    private val americanFoodViewModel by viewModels<AmericanFoodViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiiCodeNouTheme {
                americanFoodViewModel.getAmericanFood("Yogurt")
                val list = americanFoodViewModel.result
                Log.d("americanFood",list.value.toString())

                val navController = rememberNavController()

                val navigator = remember{
                    mutableStateOf("")
                }
                val currentUserSign = mainViewModel.getAuthStateLogin().collectAsState().value
                val currentUserData = mainViewModel.getAuthStateData().collectAsState().value

                if(currentUserSign)
                {
                    navigator.value="LoginInScreen"
                }
                else{
                    navigator.value="MainScreen"
                }

                //
                val user = User(
                    email = currentUserData?.email.toString(),
                    password = "DEFAULT"
                )
                userDataViewModel.getUserData(email = user.email!!)
                val userData = userDataViewModel.data.value
                val userBodyData = userDataViewModel.bodyData.value
                //

                val calendar = Calendar.getInstance().time
                val timer = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar)
                Log.d("calendarTimer",timer)

                trackedFoodViewModel.addLocalDate("empty")
                if(!currentUserSign)
                {
                    workoutUserViewModel.addWorkoutUser("workoutUser",timer)
                    trackedUser.addTrackedUser("") //Null Error (java.lang.NullPointerException
                }

                NavHost(navController = navController, startDestination = navigator.value) {
                    composable("SignUpScreen"){
                        SignUpScreen(navController = navController)
                    }
                    
                    composable("LoginInScreen"){
                        LoginInScreen(navController = navController)
                    }
                    
                    composable("MainScreen"){
                        MenuScreen(user = userData, navController = navController)
                    }

                    composable("ProfileScreen"){
                        ProfileScreen(user = userData, navController = navController)
                    }

                    composable("ProfileInfoScreen"){
                        ProfileInfoScreen(email = user.email, navController = navController)
                    }

                    composable("AddMacrosScreen"){
                        AddMacrosScreen(navController = navController)
                    }

                    composable("AddedFoodsScreen"){
                        AddedFoodsScreen(navController = navController)
                    }

                    composable("TodayStatsScreen"){
                        TodayStatsScreen(navController = navController, email = user.email)
                    }

                    composable("ChooseFitnessGoalScreen"){
                        ChooseFitnessGoal(email = user.email, navController = navController)
                    }

                    composable("EditYourNameScreen"){
                        EditYourNameScreen(navController = navController, user = userData)
                    }

                    composable("AccountDetailsScreen"){
                        AccountDetailsScreen(name = userData.username!!, navController = navController)
                    }

                    composable("EditYourPasswordScreen"){
                        EditYourPasswordScreen(navController, userData)
                    }

                    composable("DeleteAccountScreen"){
                        DeleteAccountScreen(navController, userData,userBodyData)
                    }

                    composable("MainWorkoutScreen"){
                        MainWorkoutScreen(navController)
                    }
                    
                    composable("AddWorkoutScreen"){
                        AddWorkoutScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FiiCodeNouTheme {

    }
}