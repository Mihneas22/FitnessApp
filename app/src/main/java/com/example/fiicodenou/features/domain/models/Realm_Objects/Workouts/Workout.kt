package com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Workout: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var date: String = ""
    var type: String = ""
    var numberOfExercises: Int = 0
    var exercises: RealmList<Exercise> = realmListOf()
}