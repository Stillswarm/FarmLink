package com.example.farmlinkapp1.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Seller : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var ratings: Int = 1
    var itemsListed: RealmList<SaleItem> = realmListOf()
    var user: User? = null
}