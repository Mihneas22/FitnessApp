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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.screens.AuthScreens.LoginInScreen
import com.example.fiicodenou.features.presentation.screens.AuthScreens.SignUpScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AddMacrosScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.AddedFoodsScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.MenuScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileInfoScreen
import com.example.fiicodenou.features.presentation.screens.ContentScreens.ProfileScreen
import com.example.fiicodenou.features.presentation.viewmodels.FoodViewModel
import com.example.fiicodenou.features.presentation.viewmodels.MainViewModel
import com.example.fiicodenou.features.presentation.viewmodels.SignUpViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel
import com.example.fiicodenou.ui.theme.FiiCodeNouTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val userDataViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiiCodeNouTheme {

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
                val user = User(
                    email = currentUserData?.email.toString(),
                    password = "DEFAULT"
                )
                userDataViewModel.getUserData(email = user.email!!)
                val userData = userDataViewModel.data.value

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