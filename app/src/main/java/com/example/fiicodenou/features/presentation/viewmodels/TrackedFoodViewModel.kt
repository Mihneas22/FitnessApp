package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.flow.Flow
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
}