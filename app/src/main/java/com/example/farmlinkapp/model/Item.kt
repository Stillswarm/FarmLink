package com.example.farmlinkapp.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

class Item : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var title: String = ""
    var category: Category? = null
}