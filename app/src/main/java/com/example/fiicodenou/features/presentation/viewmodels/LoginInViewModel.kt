package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginInViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel(){

    var loginInResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    fun loginInUser(email: String,password: String)
    =viewModelScope.launch {
        loginInResponse = Resource.Loading
        loginInResponse = repo.loginInUserWithEmailAndPassword(email, password)
    }
}