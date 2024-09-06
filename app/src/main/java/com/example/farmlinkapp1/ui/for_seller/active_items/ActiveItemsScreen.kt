package com.example.farmlinkapp1.ui.for_seller.active_items

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.farmlinkapp1.common.SaleItemCard
import com.example.farmlinkapp1.model.SaleItem
import kotlinx.coroutines.flow.Flow

@Composable
fun ActiveItemsScreen(
    allSaleItems: Flow<List<SaleItem>>,
    modifier: Modifier = Modifier
) {

    val allItems by allSaleItems.collectAsStateWithLifecycle(initialValue = emptyList())
    val activeItems = allItems.filter { it.active }

    LazyColumn(modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        items(activeItems) { saleItem ->
            SaleItemCard(saleItem = saleItem, title = saleItem.item!!.title)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}