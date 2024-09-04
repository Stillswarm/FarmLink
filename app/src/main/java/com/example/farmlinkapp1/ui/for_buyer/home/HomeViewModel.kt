package com.example.farmlinkapp1.ui.for_buyer.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.farmlinkapp1.data.MongoDB
import com.example.farmlinkapp1.model.Category
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {

    init {
        Log.d("fuck", "home viewmodel")
    }
    fun getAllCategories() : Flow<List<Category>> = MongoDB.getAllCategories()
}