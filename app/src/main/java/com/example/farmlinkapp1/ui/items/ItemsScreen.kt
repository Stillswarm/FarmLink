package com.example.farmlinkapp1.ui.items

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp1.common.DataCard
import org.mongodb.kbson.ObjectId

@Composable
fun ItemsScreen(
    categoryId: ObjectId,
    onClick: (ObjectId) -> Unit,
    modifier: Modifier = Modifier
) {
    val itemsViewModel: ItemsViewModel = viewModel()
    val itemsList by itemsViewModel.getAllItemsByCategory(categoryId).collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
        items(itemsList) { item ->
            DataCard(title = item.title, imageUrl = item.imageUrl, onCardClick = { onClick(item._id) })
        }
    }
}