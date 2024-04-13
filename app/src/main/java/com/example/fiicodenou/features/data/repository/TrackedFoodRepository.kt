package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.WorkoutUser
import com.example.fiicodenou.features.domain.util.Resource
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface TrackedFoodRepository {

    //Local Food
    val getAllTrackedFood: Flow<RealmList<TrackedFood>>

    suspend fun addTrackedFood(food: TrackedFood): Resource<Boolean>

    suspend fun deleteTrackedFood(name: String): Resource<Boolean>

    suspend fun clearAllTrackedFood(): Resource<Boolean>

    suspend fun calculateAllCalories(list: RealmList<TrackedFood>): Double

    suspend fun calculateAllProtein(list: RealmList<TrackedFood>): Double

    suspend fun calculateAllCarbs(list: RealmList<TrackedFood>): Double

    suspend fun calculateAllFat(list: RealmList<TrackedFood>): Double

    //Local User Data
    suspend fun getTrackedUser(name: String?): TrackedUser

    suspend fun addTrackedUser(name: String?): Resource<Boolean>

    suspend fun modifyTrackedUser(name: String?,user: TrackedUser): Resource<Boolean>

    suspend fun deleteTrackedUser(name: String?): Resource<Boolean>

    //Local User Workout
    suspend fun getWorkoutUser(name: String?): WorkoutUser

    suspend fun addWorkoutUser(name: String?,date: String?): Resource<Boolean>

    suspend fun modifyWorkoutUser(name: String?): Resource<Boolean>

    suspend fun deleteWorkoutUser(): Resource<Boolean>

    //Local Workouts

    val workouts: Flow<RealmList<Workout>>
    suspend fun addWorkout(workout: Workout): Resource<Boolean>

    suspend fun deleteWorkout(id: ObjectId): Resource<Boolean>


    //Local Date
    suspend fun localHour(day: String?): Resource<Boolean>

    suspend fun getLocalHour(): String

    suspend fun updateLocalHour(newDay: String?): Resource<Boolean>
}