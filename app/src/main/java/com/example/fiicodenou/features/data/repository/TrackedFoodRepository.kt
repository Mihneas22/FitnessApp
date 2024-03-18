package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.util.Resource
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow

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

    //Local User
    suspend fun getTrackedUser(name: String?): TrackedUser

    suspend fun addTrackedUser(name: String?): Resource<Boolean>

    suspend fun deleteTrackedUser(name: String?): Resource<Boolean>
}