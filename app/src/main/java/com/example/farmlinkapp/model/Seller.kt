
package com.example.farmlinkapp.model

import com.google.android.libraries.places.api.model.Review
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class Seller : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var profilePicUrl: String = ""
    var address: String = ""
    var phoneNumber: String = ""
    var saleItems: RealmList<SaleItems> = realmListOf()
    var rating: Double = 0.0
    var reviewsCount: Int = 0
    var quantity: Int = 0
    var distance: Double = 0.0
    var pricePerKg: Int = 0
    var reviews: RealmList<com.example.farmlinkapp.model.Review> = realmListOf()
}
