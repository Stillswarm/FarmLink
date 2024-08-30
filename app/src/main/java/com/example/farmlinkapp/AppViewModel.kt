package com.example.farmlinkapp

import androidx.lifecycle.ViewModel
import com.example.farmlinkapp.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val realm: Realm
) : ViewModel() {


    fun getCurrentScreenTitleByCategory(categoryId: ObjectId): String =
        realm.query<Category>("_id == $0", categoryId).find().first().title
}