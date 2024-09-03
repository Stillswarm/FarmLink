package com.example.farmlinkapp1.data

import com.example.farmlinkapp1.model.Category
import com.example.farmlinkapp1.model.Item
import com.example.farmlinkapp1.model.SaleItem
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoDBRepository {
    fun configureRealm()
    fun getAllCategories() : Flow<List<Category>>
    fun getAllItemsByCategoryId(categoryId: ObjectId) : Flow<List<Item>>
    fun getAllSaleItemsByItemId(itemId: ObjectId) : Flow<List<SaleItem>>
    fun getItemById(itemId: ObjectId) : Item
    fun getCategoryName(categoryId: ObjectId): String
    fun getItemName(itemId: ObjectId): String
}