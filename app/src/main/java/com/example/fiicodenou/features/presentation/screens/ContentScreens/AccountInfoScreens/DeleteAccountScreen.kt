package com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppPasswordTextField
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.models.User_Body
import com.example.fiicodenou.features.presentation.viewmodels.SignUpViewModel
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel

@Composable
fun DeleteAccountScreen(
    navController: NavController,
    user: User,
    userBody: User_Body,
    userViewModel: UserViewModel = hiltViewModel(),
    signUpViewModel: SignUpViewModel = hiltViewModel()
){
    val context = LocalContext.current


    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val password = remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF252525)
        ),
        shape = RectangleShape
    ){
        Icon(modifier = Modifier
            .padding(start = 5.dp, top = 5.dp)
            .clickable {
                navController.navigate("AccountDetailsScreen")
            },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Go Back",
            tint = Color.White
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 15.dp)) {
            Text(text = "Delete Account",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 30.sp
            )

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Enter Password:",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                fontSize = 20.sp
            )

            FitnessAppPasswordTextField(
                modifier = Modifier.padding(top = 15.dp),
                text = password.value,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })password.value=it
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

            Text(modifier = Modifier.padding(top = 15.dp),
                text = "Once you delete your account, it's permanently gone!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                fontSize = 20.sp
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                FitnessAppButton(text = "Delete",
                    onButClick = {
                        if(password.value == user.password)
                        {
                            userViewModel.deleteUser(user.email!!)
                            navController.navigate("LoginInScreen")
                        }
                    },
                    color = Color(0xF11FD3C1),
                    textColor = Color.White)
            }
        }

    }
}