package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var getUserDataResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var getDataBodyResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    val data = mutableStateOf(User())

    fun getUserData(email: String)
    =viewModelScope.launch {
        getUserDataResponse = Resource.Loading
        data.value = repo.getUserData(email)
    }

    fun modifyUserBodyInfo(email: String,weight: String,height: String)
    =viewModelScope.launch {
        getDataBodyResponse = Resource.Loading
        repo.modifyUserBodyInfo(email, weight, height)
    }

    fun logOut(){
        repo.logOut()
    }
}