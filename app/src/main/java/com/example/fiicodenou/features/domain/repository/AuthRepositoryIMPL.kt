package com.example.fiicodenou.features.domain.repository

import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.models.User_Body
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
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    auth.currentUser?.sendEmailVerification()
                }
            }.await()
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun sendEmailVerification(email: String): Resource<Boolean>
    =try{
        auth.currentUser?.sendEmailVerification()?.await()
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

    override suspend fun updatePassword(email: String,password: String): Resource<Boolean>
    =try{
        auth.confirmPasswordReset(email,password)
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
        sex: String,
        age: String,
        email: String,
        weight: String,
        height: String,
        workoutPlan: String,
        workoutDate: String,
    ): Resource<Boolean>
    =try{
        val db = fb.collection("users").document(email).collection("body_data")
        val bodyData = mutableMapOf<String, Any>()
        bodyData["user_sex"]=sex
        bodyData["user_age"]=age
        bodyData["user_weight"]=weight
        bodyData["user_height"]=height
        bodyData["user_workoutPlan"]=workoutPlan
        bodyData["user_workoutDate"]=workoutDate
        db.document("values").set(bodyData)
        Resource.Succes(true)
    }catch(ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun createUser(
        userD: User,
        userB: User_Body
    ): Resource<Boolean>
    =try{
        val user = mutableMapOf<String,Any>()
        user["user_email"] = userD.email!!
        user["user_password"] = userD.password!!
        user["user_username"]= userD.username!!
        fb.collection("users").document(userD.email).set(user)

        val bodyData = mutableMapOf<String, Any>()
        bodyData["user_sex"]=userB.sex!!
        bodyData["user_age"]=userB.age!!
        bodyData["user_weight"]=userB.weight!!
        bodyData["user_height"]=userB.height!!
        bodyData["user_workoutPlan"]=userB.workoutPlan!!
        bodyData["user_workoutDate"]=userB.workoutDate!!
        fb.collection("users").document(userD.email).collection("body_data").document("values").set(bodyData)
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun deleteUser(email: String): Resource<Boolean>
    =try{
        auth.currentUser?.delete()
        val db = fb.collection("users").document(email)
        val bdoy = fb.collection("users").document(email).collection("body_data").document("values")
        bdoy.delete()
        db.delete()
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

    override suspend fun getUserBodyData(email: String?): User_Body {
        val result = fb.collection("users").document(email!!).collection("body_data").document("values").get().await().data
        return User_Body(
            result?.get("user_age") as? String,
            result?.get("user_height") as? String,
            result?.get("user_sex") as? String,
            result?.get("user_weight") as? String,
            result?.get("user_workoutPlan") as? String,
            result?.get("user_workoutDate") as? String,
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