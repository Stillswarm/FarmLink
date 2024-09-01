package com.example.farmlinkapp.ui.sellers

import androidx.lifecycle.ViewModel
import com.example.farmlinkapp.model.Item
import com.example.farmlinkapp.model.SaleItems
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

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
}