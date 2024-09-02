package com.example.farmlinkapp1.model

data class SellersDetailData(val name: String,
                        val vegetable: String,
                        val distance: Double,
                        val quantity: Int,
                        val pricePerKg: Double,
                        val rating: Double,
                        val reviewCount:Int
) {
}