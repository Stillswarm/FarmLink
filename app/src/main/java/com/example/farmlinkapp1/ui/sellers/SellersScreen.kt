package com.example.farmlinkapp1.ui.sellers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp1.R
import com.example.farmlinkapp1.model.SaleItem
import org.mongodb.kbson.ObjectId

@Composable
fun SellersScreen(
    itemId: ObjectId,
    modifier: Modifier = Modifier
) {
    val viewModel: SellersViewModel = viewModel()
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
            if (saleItem.active) SellerItem(saleItem = saleItem)
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
            .shadow(8.dp, RoundedCornerShape(16.dp))
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
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(Color.Gray)
    ) {
        Text(
            text = "Map will be here",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SellerItem(saleItem: SaleItem) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .shadow(8.dp, RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = /*saleItem.seller!!.parent<User>().name*/"fuck",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Quantity: ${saleItem.quantityInKg}kg",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Distance: ${saleItem.distance}km",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "₹${saleItem.pricePerKg}/Kg",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    Text(
                        text = "${saleItem.seller!!.ratings} out of 5",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
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
            .padding(horizontal = 0.dp, vertical = 2.dp) // Adjust vertical padding
            .border(4.dp, Color(0xFF004D00), RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
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
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF004D00) // Dark green color
                ),
                modifier = Modifier.padding(bottom = 0.dp)
            )

            HorizontalDivider(
                color = Color(0xFF004D00),
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Raju", // Placeholder for seller name
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Quantity: 56kg", // Placeholder for quantity
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = "Distance: 1.3km", // Placeholder for distance
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "₹10/kg", // Placeholder for price
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Column {
                        Text(
                            text = "4.7 out of 5", // Placeholder for rating
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "(21 customer ratings)", // Placeholder for review count
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
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
