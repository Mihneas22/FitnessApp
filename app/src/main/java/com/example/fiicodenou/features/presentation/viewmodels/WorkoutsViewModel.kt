package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val repo: TrackedFoodRepository
): ViewModel() {

    var addWorkoutResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(true))
        private set

    var getWorkouts = repo.workouts
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addWorkout(workout: Workout)
    =viewModelScope.launch {
        repo.addWorkout(workout)
    }
}