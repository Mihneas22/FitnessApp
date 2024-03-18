package com.example.fiicodenou.features.domain.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryIMPL @Inject constructor(
    private val auth: FirebaseAuth,
    private val fb: FirebaseFirestore
): AuthRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signUpUserWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean>
    =try{
        auth.createUserWithEmailAndPassword(email,password).await()
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun observeAuthState(observer: (FirebaseUser?) -> Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            observer(firebaseAuth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
    }

    override suspend fun loginInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean>
    =try{
        auth.signInWithEmailAndPassword(email,password).await()
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun modifyUserDataInfo(
        email: String?,
        password: String?,
        username: String?,
    ): Resource<Boolean>
    =try{
        val db = fb.collection("users").document(email!!)
        val user = mutableMapOf<String,Any>()
        user["user_email"]=email
        user["user_password"]=password!!
        user["user_username"]=username!!
        db.set(user)
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun modifyUserBodyInfo(
        email: String,
        weight: String,
        height: String
    ): Resource<Boolean>
    =try{
        val db = fb.collection("users").document(email).collection("body_data")
        val bodyData = mutableMapOf<String, Any>()
        bodyData["user_weight"]=weight
        bodyData["user_height"]=height
        db.document("values").set(bodyData)
        Resource.Succes(true)
    }catch(ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun createUser(
        email: String?,
        password: String?,
        name: String?,
    ): Resource<Boolean>
    =try{
        val user = mutableMapOf<String,Any>()
        user["user_email"] = email!!
        user["user_password"] = password!!
        user["user_username"]=name!!
        fb.collection("users").document(email).set(user)
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getUserData(email: String?): User {
        val result = fb.collection("users").document(email!!).get().await().data
        return User(
            result?.get("user_email") as? String,
            result?.get("user_password") as? String,
            result?.get("user_username") as? String,
        )
    }

    override fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser == null)

    override fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser)

    override fun logOut(){
        auth.signOut()
    }
}