package com.example.fiicodenou.features.domain.models.Realm_Objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class FoodApiLocal: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var category: String = ""
    var name: String = ""
    var protein: String = ""
    var carbs: String = ""
    var sugar: String = ""
    var fat: String = ""
    var fibers: String = ""
    var nutrientCode: String = ""
}