package com.example.farmlinkapp1.navigation

import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

@Serializable
data object Authentication  //route for nested navigation

@Serializable
data object SignIn

@Serializable
data object UserType

@Serializable
data object UserDetails

@Serializable
data object MainApp //route for nested navigation

@Serializable
data object Home

@Serializable
data class Items(val categoryId: ObjectId)

@Serializable
data class SellerInventory(val itemId: ObjectId)

@Serializable
data class SellerDetails(val sellerName: String)

@Serializable
data object Chat