package com.example.fiicodenou.features.data.repository

import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.FoodsAmericanDB
import com.example.fiicodenou.features.domain.util.Resource

interface FoodRepository {
    suspend fun addFoodData(food: Food): Resource<Boolean>

    suspend fun getFoodData(list: (List<Food>)->Unit)

    suspend fun getApprovedFoodList(list: List<Food>): List<Food>

    suspend fun getFoodsRelatedToName(base: String, list: List<Food>): List<Food>

    suspend fun getFoodsAmericanDatabase(name: String,list: (List<FoodsAmericanDB>)->Unit)
}