package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.WorkoutUser
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutUserViewModel @Inject constructor(
    private val repo: TrackedFoodRepository
): ViewModel() {
    var addWorkoutUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var deleteWorkoutUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    fun addWorkoutUser(name: String?,date: String?)
    =viewModelScope.launch {
        repo.addWorkoutUser(name,date)
    }

    fun deleteWorkoutUser()
    =viewModelScope.launch {
        repo.deleteWorkoutUser()
    }
}