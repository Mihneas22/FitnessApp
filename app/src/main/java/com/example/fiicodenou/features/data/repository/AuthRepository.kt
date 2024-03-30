package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.models.User_Body
import com.example.fiicodenou.features.domain.util.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signUpUserWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun loginInUserWithEmailAndPassword(email: String,password: String): Resource<Boolean>

    suspend fun sendEmailVerification(email: String): Resource<Boolean>

    suspend fun updatePassword(email: String,password: String): Resource<Boolean>

    suspend fun observeAuthState(observer: (FirebaseUser?) -> Unit)

    suspend fun createUser(userD: User, userB: User_Body): Resource<Boolean>

    suspend fun deleteUser(email: String): Resource<Boolean>

    suspend fun modifyUserDataInfo(email: String?,password: String?,username: String?): Resource<Boolean>

    suspend fun modifyUserBodyInfo(sex: String, age: String,email: String,weight: String,height: String,workoutPlan: String,
                                   workoutDate: String): Resource<Boolean>

    suspend fun getUserData(email: String?): User

    suspend fun getUserBodyData(email: String?): User_Body

    fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean>

    fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?>

    fun logOut()
}