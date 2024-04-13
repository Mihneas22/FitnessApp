package com.example.fiicodenou.features.domain.repository

import androidx.compose.runtime.mutableDoubleStateOf
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedHour
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.WorkoutUser
import com.example.fiicodenou.features.domain.util.Resource
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class TrackedFoodRepositoryIMPL @Inject constructor(
    private val realm: Realm
): TrackedFoodRepository {


    //Local Food
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
            val foodQuery = this.query<TrackedFood>().find()
            delete(foodQuery)
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

    //Local User
    override suspend fun getTrackedUser(name: String?): TrackedUser {
        return realm.query<TrackedUser>("name == $0", name).find().first()
    }

    override suspend fun addTrackedUser(name: String?): Resource<Boolean>
    =try{
        val db = realm.query<TrackedUser>().find()
        if(db.isEmpty())
        {
            realm.write {
                val newUser = TrackedUser().apply {
                    this.name = name
                }
                copyToRealm(newUser,UpdatePolicy.ALL)
            }
        }

        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun modifyTrackedUser(name: String?,user: TrackedUser): Resource<Boolean>
    =try{
        realm.write {
            val newUser = this.query<TrackedUser>("name == $0",name).find().first()
            newUser.hasExercised=user.hasExercised
            newUser.water=user.water
            copyToRealm(newUser,UpdatePolicy.ALL)
        }

        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun deleteTrackedUser(name: String?): Resource<Boolean>
    =try{
        realm.write {
            val queryUser: TrackedUser = this.query<TrackedUser>().find().first()
            delete(queryUser)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun localHour(day: String?): Resource<Boolean>
    =try{
        realm.write {
            val db = realm.query<TrackedHour>().find()
            if(db.isEmpty())
            {
                val hour = TrackedHour().apply {
                    if (day != null) {
                        this.day = day
                    }
                }
                copyToRealm(hour,UpdatePolicy.ALL)
            }
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getLocalHour(): String {
        return realm.query<TrackedHour>().find().first().day
    }

    override suspend fun updateLocalHour(newDay: String?): Resource<Boolean>
    =try{
        realm.write {
            val valueHour = this.query<TrackedHour>().find().first()
            if (newDay != null) {
                valueHour.day=newDay
            }
            copyToRealm(valueHour,UpdatePolicy.ALL)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    //Local Workout User

    override suspend fun addWorkoutUser(name: String?,date: String?): Resource<Boolean>
    =try{
        realm.write {
            val query = realm.query<WorkoutUser>().find()
            if(query.isEmpty()){
                val workoutUserTracked = WorkoutUser().apply {
                    if (name != null && date != null) {
                        this.name = name
                        this.date = date
                    }
                }
                copyToRealm(workoutUserTracked,UpdatePolicy.ALL)
            }
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getWorkoutUser(name: String?): WorkoutUser {
        TODO("Not yet implemented")
    }

    override suspend fun modifyWorkoutUser(name: String?): Resource<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkoutUser(): Resource<Boolean>
    =try{
        realm.write {
            val user = this.query<WorkoutUser>().find().first()
            delete(user)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    //Local Workouts

    override val workouts: Flow<RealmList<Workout>>
        get() = realm.query<WorkoutUser>()
            .find()
            .first()
            .workouts.asFlow()
            .map {results->
                results.list.toRealmList()
            }
    override suspend fun addWorkout(workout: Workout): Resource<Boolean>
    =try{
        realm.write {
            val user = this.query<WorkoutUser>().find().first()
            user.workouts.add(workout)
            user.workout_status_week+=1
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun deleteWorkout(id: ObjectId): Resource<Boolean>
    =try{
        realm.write {
            val workout: Workout = this.query<Workout>("_id == $0",id).find().first()
            delete(workout)
        }
        Resource.Succes(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }
}