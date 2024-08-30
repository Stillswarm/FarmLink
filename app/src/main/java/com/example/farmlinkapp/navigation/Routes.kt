package com.example.farmlinkapp.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Register

@Serializable
data object MainApp //route for nested navigation

@Serializable
data object Home

@Serializable
data class Items(val categoryId: String)

@Serializable
data class SellerInventory(val item: String)

@Serializable
data class SellerDetails(val sellerName: String)

@Serializable
data object Chat