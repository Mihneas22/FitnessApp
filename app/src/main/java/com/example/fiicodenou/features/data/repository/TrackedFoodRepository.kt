package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.util.Resource
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow

interface TrackedFoodRepository {
    val getAllTrackedFood: Flow<RealmList<TrackedFood>>

    suspend fun addTrackedFood(food: TrackedFood): Resource<Boolean>

    suspend fun deleteTrackedFood(name: String): Resource<Boolean>

    suspend fun clearAllTrackedFood(): Resource<Boolean>

    suspend fun calculateAllCalories(list: RealmList<TrackedFood>): Double

    suspend fun calculateAllProtein(): Double

    suspend fun calculateAllCarbs(): Double

    suspend fun calculateAllFat(): Double
}