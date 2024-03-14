package com.example.fiicodenou.features.domain.models.Realm_Objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TrackedFood: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var calories: String = ""
    var weight: String = ""
    var protein: String = ""
    var carbohydrates: String = ""
    var fat: String = ""
}