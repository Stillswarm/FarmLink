package com.example.farmlinkapp.ui.seller_details

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.unit.lerp
import com.example.farmlinkapp.model.SellersDetailData
import kotlin.math.min

class SellerDetailsViewModel : ViewModel() {
    private val maxMapHeight = 200.dp
    private val minMapHeight = 50.dp

    var mapHeight by mutableStateOf(maxMapHeight)
        private set

    fun updateMapHeight(scrollOffset: Int) {
        mapHeight = lerp(
            start = maxMapHeight,
            stop = minMapHeight,
            fraction = min(1f, scrollOffset / 300f)
        )
    }

    fun filterSellers(sellers: List<SellersDetailData>, vegetable: String): List<SellersDetailData> {
        return sellers.filter { it.vegetable == vegetable }
    }
}
