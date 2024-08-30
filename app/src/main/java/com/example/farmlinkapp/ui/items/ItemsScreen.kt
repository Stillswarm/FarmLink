package com.example.farmlinkapp.ui.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.farmlinkapp.common.DataCard
import com.example.farmlinkapp.model.Category

@Composable
fun ItemsScreen(
    categoryId: String? = null,
    modifier: Modifier = Modifier
) {
//    LazyColumn {
//        items(itemsList) { item ->
//            DataCard(title = item.title!!, imageUrl = item.imageUrl!!, onCardClick = {})
//        }
//    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Category: $categoryId")
    }
}