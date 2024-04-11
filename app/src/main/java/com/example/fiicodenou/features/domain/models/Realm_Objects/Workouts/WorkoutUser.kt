package com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class WorkoutUser: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var date: String = ""
    var workout_status_week: Int = 0
    var workouts: RealmList<Workout> = realmListOf()
}