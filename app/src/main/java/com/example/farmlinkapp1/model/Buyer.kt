package com.example.farmlinkapp1.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId

class Buyer : RealmObject {
    var _id: ObjectId = ObjectId()
    var itemsBought: RealmList<SaleItem> = realmListOf()
}