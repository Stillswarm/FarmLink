package com.example.farmlinkapp.ui.sellers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.lifecycle.ViewModel
import com.example.farmlinkapp.model.Item
import com.example.farmlinkapp.model.SaleItems
import com.example.farmlinkapp.model.SellersDetailData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class SellersViewModel @Inject constructor(
    private val realm: Realm
) : ViewModel() {

    fun getSellersByItemId(itemId: ObjectId) : Flow<List<SaleItems>> {
        return realm.query<SaleItems>("item._id == $0", itemId).asFlow().map { it.list }
    }

    fun getItemById(itemId: ObjectId) : Item {
        return realm.query<Item>("_id == $0", itemId).first().find()!!
    }

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