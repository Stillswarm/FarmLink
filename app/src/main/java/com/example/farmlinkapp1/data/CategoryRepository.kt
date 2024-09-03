package com.example.farmlinkapp1.data

import com.example.farmlinkapp1.model.Category
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CategoryRepository {
    fun getAllCategories() : Flow<List<Category>>
}

class CategoryRepositoryImpl(
    private val realm: Realm
) {
    fun getAllCategories() : Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }
}