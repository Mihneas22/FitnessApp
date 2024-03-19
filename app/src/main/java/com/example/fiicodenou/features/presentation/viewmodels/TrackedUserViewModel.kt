package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.User
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackedUserViewModel @Inject constructor(
    private val repo: TrackedFoodRepository
): ViewModel() {

    var addTrackedUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var deleteTrackedUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var trackedUser = TrackedUser()
    fun getTrackedUser(name: String?)
    =viewModelScope.launch {
        trackedUser = repo.getTrackedUser(name)
    }

    fun addTrackedUser(name: String?)
    =viewModelScope.launch {
        addTrackedUserResponse = Resource.Loading
        repo.addTrackedUser(name)
    }

    fun modifyTrackedUser(name: String?,user: TrackedUser)
    =viewModelScope.launch {
        repo.modifyTrackedUser(name, user)
    }
    fun deleteTrackedUser(name: String?)
    =viewModelScope.launch {
        deleteTrackedUserResponse = Resource.Loading
        repo.deleteTrackedUser(name)
    }
}