package com.example.farmlinkapp1.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SaleItems : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var item: Item? = null
    var seller: Seller? = null
    var quantityInKg: Double = 0.0
    var pricePerKg: Double = 0.0
    var distance: Double = 0.0
    var rating: Int = 1
}