package com.example.farmlinkapp.ui.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmlinkapp.common.DataCard
import org.mongodb.kbson.ObjectId

@Composable
fun ItemsScreen(
    categoryId: ObjectId,
    modifier: Modifier = Modifier
) {
    val itemsViewModel = hiltViewModel<ItemsViewModel>()
    val itemsList by itemsViewModel.getAllItemsByCategory(categoryId).collectAsState(initial = emptyList())
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.fillMaxWidth()) {
        items(itemsList) { item ->
            DataCard(title = item.title, imageUrl = item.imageUrl, onCardClick = {})
        }
    }
}