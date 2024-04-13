package com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Exercise: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var targetedMuscleGroup: String = ""
    var duration: Int = 0
    var sets: Int = 0
    var reps: Int = 0
}