package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.util.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signUpUserWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun loginInUserWithEmailAndPassword(email: String,password: String): Resource<Boolean>

    suspend fun observeAuthState(observer: (FirebaseUser?) -> Unit)

    suspend fun createUser(email: String?,password: String?,name: String?): Resource<Boolean>

    suspend fun modifyUserDataInfo(email: String?,password: String?,username: String?): Resource<Boolean>

    suspend fun modifyUserBodyInfo(sex: String, age: String,email: String,weight: String,height: String): Resource<Boolean>

    suspend fun getUserData(email: String?): User

    fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean>

    fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?>

    fun logOut()
}