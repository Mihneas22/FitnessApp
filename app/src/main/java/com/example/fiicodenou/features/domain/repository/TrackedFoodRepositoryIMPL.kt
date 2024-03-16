package com.example.fiicodenou.features.domain.repository

import androidx.compose.runtime.mutableDoubleStateOf
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Food
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.util.Resource
import com.google.android.play.integrity.internal.i
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrackedFoodRepositoryIMPL @Inject constructor(
    private val realm: Realm
): TrackedFoodRepository {

    override val getAllTrackedFood: Flow<RealmList<TrackedFood>>
        get() = realm.query<TrackedFood>()
            .find()
            .asFlow()
            .map {results->
                results.list.toRealmList()
            }

    override suspend fun addTrackedFood(food: TrackedFood): Resource<Boolean>
    =try{
        realm.write {
            val foodAdd = TrackedFood().apply {
                this.name = food.name
                this.weight = food.weight
                this.calories = food.calories
                this.protein = food.protein
                this.carbohydrates = food.carbohydrates
                this.fat = food.fat
            }
            copyToRealm(foodAdd,UpdatePolicy.ALL)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun deleteTrackedFood(name: String): Resource<Boolean>
    =try{
        realm.write {
            val liveFood: TrackedFood = this.query<TrackedFood>("name == $0",name).find().first()
            delete(liveFood)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun clearAllTrackedFood(): Resource<Boolean>
    =try{
        realm.write {
            deleteAll()
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun calculateAllCalories(list: RealmList<TrackedFood>): Double {
        val sum = mutableDoubleStateOf(0.0)
        for(item in list){
            sum.doubleValue += item.calories.toDouble()
        }

        return sum.doubleValue
    }

    override suspend fun calculateAllProtein(list: RealmList<TrackedFood>): Double {
        val sum = mutableDoubleStateOf(0.0)
        for(item in list){
            sum.doubleValue += item.protein.toDouble()
        }

        return sum.doubleValue
    }

    override suspend fun calculateAllCarbs(list: RealmList<TrackedFood>): Double {
        val sum = mutableDoubleStateOf(0.0)
        for(item in list){
            sum.doubleValue += item.carbohydrates.toDouble()
        }

        return sum.doubleValue
    }

    override suspend fun calculateAllFat(list: RealmList<TrackedFood>): Double {
        val sum = mutableDoubleStateOf(0.0)
        for(item in list){
            sum.doubleValue += item.fat.toDouble()
        }

        return sum.doubleValue
    }
}