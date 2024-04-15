package com.example.fiicodenou.features.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiicodenou.features.data.repository.FoodRepository
import com.example.fiicodenou.features.domain.models.FoodsAmericanDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmericanFoodViewModel @Inject constructor(
    private val repo: FoodRepository
): ViewModel(){

    var result = mutableStateOf<List<FoodsAmericanDB>>(emptyList())
    fun getAmericanFood(name: String)
    =viewModelScope.launch {
        repo.getFoodsAmericanDatabase(name){
            result.value = it
        }
    }
}