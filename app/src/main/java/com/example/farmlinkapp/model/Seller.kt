package com.example.farmlinkapp.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Seller : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var profilePicUrl: String = ""
    var address: String = ""
    var phoneNumber: String = ""
    var saleItems: RealmList<SaleItems> = realmListOf()
}