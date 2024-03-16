package com.example.fiicodenou.features.domain.repository

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fiicodenou.features.data.repository.FoodRepository
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FoodRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): FoodRepository {

    override suspend fun addFoodData(food: Food): Resource<Boolean>
            =try{
        val db = fb.collection("foods").document(food.name)
        val foodMap = mutableMapOf<String,Any>()
        foodMap["food_name"] = food.name
        foodMap["food_weight"] = food.weight
        foodMap["food_calories"] = food.calories
        foodMap["food_protein"] = food.protein
        foodMap["food_carbohydrates"] = food.carbohydrates
        foodMap["food_fat"] = food.fat
        foodMap["food_status"] = food.approved

        db.set(foodMap)
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getFoodData(list: (List<Food>)->Unit) {
        fb.collection("foods")
            .get()
            .addOnSuccessListener { documents ->
                val items = mutableListOf<Food>()
                for (document in documents) {
                    val data = document.data
                    val item = (data["food_status"] as? Boolean)?.let {
                        Food(
                            data["food_name"] as String,
                            data["food_calories"] as String,
                            data["food_weight"] as String,
                            data["food_protein"] as String,
                            data["food_carbohydrates"] as String,
                            data["food_fat"] as String,
                            it
                        )
                    }
                    if (item != null) {
                        items.add(item)
                    }
                }
                list(items)
            }
            .addOnFailureListener {
                Log.e("Firestore", "Error getting documents: ", it)
            }
    }

    override suspend fun getApprovedFoodList(list: List<Food>): List<Food> {
        val newList = mutableListOf<Food>()
        val i = mutableIntStateOf(0)
        for(food in list){
            if(food.approved){
                newList.add(i.intValue,food)
                i.intValue += 1
            }
        }
        return newList
    }

    override suspend fun getFoodsRelatedToName(base: String, list: List<Food>): List<Food> {
        val newList = mutableListOf<Food>()
        val i = mutableIntStateOf(0)
        for(food in list){
            if(food.name.contains(base)){
                newList.add(i.intValue,food)
                i.intValue += 1
            }
        }
        return newList
    }
}