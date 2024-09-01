package com.example.farmlinkapp.ui.sellers

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.farmlinkapp.R
import com.example.farmlinkapp.model.SaleItems
import org.mongodb.kbson.ObjectId

@Composable
fun SellersScreen(
    itemId: ObjectId,
    modifier: Modifier = Modifier
) {
    val viewModel: SellersViewModel = hiltViewModel()
    val scrollState = rememberLazyListState()
    val saleItemsList by viewModel.getSellersByItemId(itemId).collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    LaunchedEffect(remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }) {
        viewModel.updateMapHeight(scrollState.firstVisibleItemScrollOffset)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState
    ) {
        item {
            CurvedImageBox()
        }
        item {
            ShrinkableMapBox(viewModel.mapHeight)
        }
        item {
            RecommendedSellerCard()
        }
        items(saleItemsList) { saleItem ->
            SellerItem(saleItem = saleItem)
        }
    }
}

@Composable
fun CurvedImageBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .padding(1.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.category_vegetable),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ShrinkableMapBox(mapHeight: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(mapHeight)
            .clip(RoundedCornerShape(16.dp))
            .shadow(4.dp, RoundedCornerShape(16.dp))
    ) {
        Text(
            text = "Map will be here",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SellerItem(saleItem: SaleItems) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = saleItem.seller!!.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Quantity: ${saleItem.quantityInKg}kg",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Distance: ${saleItem.distance}km",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "₹${saleItem.pricePerKg}/Kg",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    Text(
                        text = "${saleItem.rating} out of 5",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    /* TODO: IMPLEMENT REVIEW COUNT MECHANISM */
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        text = "(${saleItem.reviewCount} customer ratings)",
//                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//                    )
                }
            }
        }
    }
}

@Composable
fun RecommendedSellerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 2.dp)
            .border(4.dp, Color(0xFF004D00), RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Recommended",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                modifier = Modifier.padding(bottom = 0.dp)
            )

            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Raju", // Placeholder for seller name
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Quantity: 56kg", // Placeholder for quantity
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Distance: 1.3km", // Placeholder for distance
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "₹10/kg", // Placeholder for price
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Column {
                        Text(
                            text = "4.7 out of 5", // Placeholder for rating
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "(21 customer ratings)", // Placeholder for review count
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSellerDetailsScreen() {
//    val sampleSellers = listOf(
//        SellersDetailData("Brijesh Lal", "Potato", 2.0, 67, 15.0, 4.2, 12),
//        SellersDetailData("Shyamu", "Potato", 2.1, 67, 17.0, 4.2, 12),
//        SellersDetailData("Chotu", "Potato", 2.7, 43, 12.0, 4.2, 12),
//        SellersDetailData("Brijesh Lal", "Potato", 2.0, 67, 15.0, 4.2, 12),
//        SellersDetailData("Shyamu", "Potato", 2.1, 67, 17.0, 4.2, 12),
//        SellersDetailData("Chotu", "Potato", 2.7, 43, 12.0, 4.2, 12)
//    )
//    SellersScreen(
//        sellers = sampleSellers,
//        vegetable = "Potato",
//    )
//}
//
