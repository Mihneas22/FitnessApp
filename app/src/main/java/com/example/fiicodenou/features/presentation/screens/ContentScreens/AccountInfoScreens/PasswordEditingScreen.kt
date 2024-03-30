package com.example.fiicodenou.features.presentation.screens.ContentScreens.AccountInfoScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.presentation.viewmodels.UserViewModel

@Composable
fun EditYourPasswordScreen(
    navController: NavController,
    user: User,
    userViewModel: UserViewModel = hiltViewModel()
){
    val context = LocalContext.current

    val passwordOld = remember{
        mutableStateOf("")
    }

    val passwordNew = remember{
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
            .padding(start = 15.dp, top = 15.dp)
        ) {
            Text(text = "Enter Your Old Password",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 30.sp
            )

            FitnessAppTextField(
                modifier = Modifier
                    .padding(top = 10.dp, end = 15.dp)
                    .fillMaxWidth(),
                text = passwordOld.value,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })passwordOld.value = it
                }, label = "Password",
                color = Color.DarkGray,
                textColor = Color.White
            )

            Text(modifier = Modifier.padding(top = 70.dp),
                text = "Enter Your New Password",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 30.sp
            )

            FitnessAppTextField(
                modifier = Modifier
                    .padding(top = 10.dp, end = 15.dp)
                    .fillMaxWidth(),
                text = passwordNew.value,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })passwordNew.value = it
                }, label = "Password",
                color = Color.DarkGray,
                textColor = Color.White
            )

            Text(modifier = Modifier.padding(top = 15.dp),
                text = "Make sure to have a powerful password that can protect you from external threats",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 20.sp
            )
            FitnessAppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 380.dp, end = 15.dp),
                text = "Save",
                onButClick = {
                    Log.d("userData",user.password!!)
                    Log.d("userData",passwordOld.value)
                    if(passwordOld.value != user.password)
                    {
                        Toast.makeText(context,"Passwords don't match up!",Toast.LENGTH_SHORT).show()
                    }else if(passwordNew.value == passwordOld.value){
                        Toast.makeText(context,"Passwords are the same!",Toast.LENGTH_SHORT).show()
                    }else{
                        userViewModel.modifyUserDataInfo(user.email!!,passwordNew.value,user.username!!)
                        if(userViewModel.currentUser!=null){
                            userViewModel.currentUser!!.updatePassword(passwordNew.value)
                        }
                        navController.navigate("AccountDetailsScreen")
                    }
                },
                color = Color(0xF11FD3C1),
                textColor = Color.White)
        }
    }
}