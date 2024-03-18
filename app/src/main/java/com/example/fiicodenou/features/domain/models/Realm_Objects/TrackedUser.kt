package com.example.fiicodenou.features.domain.models.Realm_Objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TrackedUser(): RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String? = ""
    var hasExercised: Boolean? = false
}