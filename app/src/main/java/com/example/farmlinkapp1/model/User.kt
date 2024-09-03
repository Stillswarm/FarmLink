package com.example.farmlinkapp1.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/* TODO: LOCATION DETAILS */

class User : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var ownerId: String = ""
    var name: String = ""
    var profilePicUrl: String = ""
    var address: String = ""
    var phoneNumber: String = ""
    var buyer: Buyer? = null
    var seller: Seller? = null
}