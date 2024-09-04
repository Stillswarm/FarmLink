package com.example.farmlinkapp1.ui.for_buyer.sellers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.lifecycle.ViewModel
import com.example.farmlinkapp1.data.MongoDB
import com.example.farmlinkapp1.model.SaleItem
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import kotlin.math.min

class SellersViewModel : ViewModel() {

    fun getSellersByItemId(itemId: ObjectId) : Flow<List<SaleItem>> = MongoDB.getAllSaleItemsByItemId(itemId)

    fun getItemById(itemId: ObjectId) = MongoDB.getItemById(itemId)

    fun getItemImageById(itemId: ObjectId) = MongoDB.getItemImageById(itemId)

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
}