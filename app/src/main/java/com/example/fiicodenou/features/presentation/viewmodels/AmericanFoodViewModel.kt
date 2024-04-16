package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.FoodRepository
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.FoodsAmericanDB
import com.example.fiicodenou.features.domain.models.Realm_Objects.FoodApiLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmericanFoodViewModel @Inject constructor(
    private val repo: FoodRepository,
    private val repoTracked: TrackedFoodRepository
): ViewModel(){

    var result = mutableStateOf<List<FoodsAmericanDB>>(emptyList())

    var database = mutableStateOf<Boolean>(true)

    var food = MutableStateFlow<RealmList<FoodApiLocal>>(realmListOf()).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        realmListOf()
    )
    fun getAmericanFood()
    =viewModelScope.launch {
        repo.getFoodsAmericanDatabase(){
            result.value = it
        }
    }

    fun checkDatabase()
    =viewModelScope.launch {
        database.value = repoTracked.checkDatabase()
    }
    fun addAmericanFoodToLocalDB(list: List<FoodsAmericanDB>)
    =viewModelScope.launch {
        repoTracked.addFirebaseFood(list)
    }

    fun getDBFood(name: String)
    =viewModelScope.launch {
        food = repoTracked.getDBFood(name).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            realmListOf()
        )
    }
}