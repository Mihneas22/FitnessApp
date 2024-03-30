package com.example.fiicodenou.features.presentation.screens.AuthScreens

import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.fiicodeapp.features.presentation.components.FitnessAppPasswordTextField
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.R
import com.example.fiicodenou.features.domain.util.Resource
import com.example.fiicodenou.features.presentation.viewmodels.LoginInViewModel
import com.example.fiicodenou.features.presentation.viewmodels.SignUpViewModel

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
){
    Column {
        HeaderSignUp()
        MainSignUp(signUpViewModel, navController)
    }
}

@Composable
fun HeaderSignUp(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                painter = painterResource(id = R.drawable.workingman),
                contentDescription = "Working",
                contentScale = ContentScale.FillWidth
            )
            Text(modifier = Modifier.padding(top=30.dp),
                text = "Start Your Journey Now!",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp,
                color = Color(0xFF696969)
            )
        }
    }
}

@Composable
fun MainSignUp(
    signUpViewModel: SignUpViewModel,
    navController: NavController
){

    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            FitnessAppTextField(
                modifier = Modifier.padding(bottom = 20.dp),
                text = username,
                onTextChange = {
                    if(it.all {char->
                        char.isDefined()
                    })username=it
                },
                label = "Name",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppTextField(
                modifier = Modifier.padding(bottom = 20.dp),
                text = email,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })email=it
                },
                label = "Email",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1)
            )

            FitnessAppPasswordTextField(
                text = password,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })password=it
                },
                label = "Password",
                color = Color(0xFF252525),
                textColor = Color(0xF11FD3C1),
                visualState = passwordVisible,
                icon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                FitnessAppButton(
                    modifier = Modifier.padding(end = 7.dp),
                    text = "Cancel",
                    onButClick = {
                        navController.navigate("LoginInScreen")
                    },
                    color = Color(0xFFFFFFFF),
                    textColor = Color.Black
                )

                FitnessAppButton(
                    modifier = Modifier.padding(start = 7.dp),
                    text = "Sign Up",
                    onButClick = {
                        if(username.isEmpty()){
                            Toast.makeText(context,"Enter a name",Toast.LENGTH_SHORT).show()
                        }else if(email.isEmpty()){
                            Toast.makeText(context,"Enter an email",Toast.LENGTH_SHORT).show()
                        }else if(password.length<8){
                            Toast.makeText(context,"Enter a password with more than 8 letters",Toast.LENGTH_SHORT).show()
                        }else{
                            when(val signUpResponse = signUpViewModel.signUpResponse) {
                                is Resource.Loading -> ProgressBar(context)
                                is Resource.Succes -> {
                                    signUpViewModel.signUpUser(email, password)
                                    signUpViewModel.createUser(email, password,username)
                                    navController.navigate("LoginInScreen")
                                }
                                is Resource.Failure -> signUpResponse.apply {
                                    Toast.makeText(context,"Error: $ex",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White
                )
            }
        }
    }
}
