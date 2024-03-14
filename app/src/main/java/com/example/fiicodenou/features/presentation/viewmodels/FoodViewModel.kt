package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.FoodRepository
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodRepository
): ViewModel(){
    var createFoodResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var getFoodDataResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var getFoodApprovedDataResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    var getFoodsRelatedToNameResponse by mutableStateOf<Resource<Boolean>>(Resource.Succes(false))
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var result = mutableStateOf<List<Food>>(emptyList())
    var resultApprovedList = mutableStateOf<List<Food>>(emptyList())
    var resultRelatedToNameList = mutableStateOf<List<Food>>(emptyList())

    init {
        loadFood()
    }

    fun getApprovedFood(list: List<Food>)
    =viewModelScope.launch {
        getFoodApprovedDataResponse = Resource.Loading
        resultApprovedList.value = repo.getApprovedFoodList(list)
    }

    fun getFoodsRelatedToName(base: String,list: List<Food>)
            =viewModelScope.launch {
        getFoodsRelatedToNameResponse = Resource.Loading
        resultRelatedToNameList.value = repo.getFoodsRelatedToName(base, list)
    }

    fun createFood(food: Food)
    =viewModelScope.launch{
        createFoodResponse = Resource.Loading
        repo.addFoodData(food)
    }

    fun loadFood()
    =viewModelScope.launch {
        _isLoading.value = true
        delay(3000L)
        _isLoading.value = false
    }

    fun getFood()
    =viewModelScope.launch {
        getFoodDataResponse = Resource.Loading
        repo.getFoodData {
            result.value = it
        }
    }
}