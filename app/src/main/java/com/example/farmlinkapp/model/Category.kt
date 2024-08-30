package com.example.farmlinkapp.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

class Category : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var title: String = ""
    var imageUrl: String = ""
    var items: RealmList<Item> = realmListOf()
}