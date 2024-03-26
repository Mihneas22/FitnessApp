package com.example.fiicodenou.features.domain.models.Realm_Objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TrackedHour : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var day: String = ""
}