package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.domain.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {

    var signUpResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var createUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    fun signUpUser(email: String,password: String)
    =viewModelScope.launch {
        signUpResponse = Resource.Loading
        repo.signUpUserWithEmailAndPassword(email, password)
    }

    fun createUser(email: String?,password: String?,username: String?)
    =viewModelScope.launch {
        createUserResponse = Resource.Loading
        repo.createUser(email, password,username)
    }
}