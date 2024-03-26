package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackedFoodViewModel @Inject constructor(
    private val repo: TrackedFoodRepository
): ViewModel() {
    var addTrackedFoodResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var deleteTrackedFoodResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var deleteAllTrackedFoodResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var localDate = mutableStateOf("")
    var caloriesFull = mutableDoubleStateOf(0.0)
    var proteinFull = mutableDoubleStateOf(0.0)
    var carbohydratesFull = mutableDoubleStateOf(0.0)
    var fatFull = mutableDoubleStateOf(0.0)

    val getAllTrackedFood = repo.getAllTrackedFood.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        realmListOf()
    )

    fun addTrackedFood(food: TrackedFood)
    =viewModelScope.launch {
        addTrackedFoodResponse = Resource.Loading
        repo.addTrackedFood(food)
        addTrackedFoodResponse = Resource.Succes(true)
    }

    fun deleteTrackedFood(name: String)
    =viewModelScope.launch {
        deleteTrackedFoodResponse = Resource.Loading
        repo.deleteTrackedFood(name)
        deleteTrackedFoodResponse = Resource.Succes(true)
    }

    fun deleteAllTrackedFood()
    =viewModelScope.launch {
        deleteAllTrackedFoodResponse = Resource.Loading
        repo.clearAllTrackedFood()
        deleteAllTrackedFoodResponse = Resource.Succes(true)
    }

    fun calculateAllCalories(list: RealmList<TrackedFood>)
    =viewModelScope.launch {
        caloriesFull.doubleValue =repo.calculateAllCalories(list)
    }

    fun calculateAllProtein(list: RealmList<TrackedFood>)
            =viewModelScope.launch {
        proteinFull.doubleValue =repo.calculateAllProtein(list)
    }

    fun calculateAllCarbohydrates(list: RealmList<TrackedFood>)
            =viewModelScope.launch {
        carbohydratesFull.doubleValue =repo.calculateAllCarbs(list)
    }

    fun calculateAllFat(list: RealmList<TrackedFood>)
            =viewModelScope.launch {
        fatFull.doubleValue =repo.calculateAllFat(list)
    }

    fun addLocalDate(day: String?)
    =viewModelScope.launch {
        repo.localHour(day)
    }

    fun getLocalDate()
            =viewModelScope.launch {
        localDate.value = repo.getLocalHour()
    }

    fun updateLocalDate(newDate: String?)
    =viewModelScope.launch {
        repo.updateLocalHour(newDate)
    }
}