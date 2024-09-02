package com.example.farmlinkapp1.ui.items

import androidx.lifecycle.ViewModel
import com.example.farmlinkapp1.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val realm: Realm
) : ViewModel() {

    fun getAllItemsByCategory(categoryId: ObjectId) : Flow<List<Item>> {
        return realm.query<Item>(query = "category._id == $0", categoryId).asFlow().map { it.list }
    }
}