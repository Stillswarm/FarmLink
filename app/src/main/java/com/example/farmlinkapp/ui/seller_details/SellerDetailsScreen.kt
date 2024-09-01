package com.example.farmlinkapp.ui.seller_details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp.R
import com.example.farmlinkapp.model.SellersDetailData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SellerDetailsScreen(sellers: List<SellersDetailData>,
                        vegetable: String,
                        viewModel: SellerDetailsViewModel = viewModel(),
                        modifier: Modifier = Modifier) {

    val scrollState = rememberLazyListState()

    Surface(modifier = Modifier
        .background(Color(0xFF82B64B))
        .fillMaxSize()) {

        LaunchedEffect(scrollState.firstVisibleItemScrollOffset) {
            viewModel.updateMapHeight(scrollState.firstVisibleItemScrollOffset)
        }

        Scaffold(
            topBar = { ColoredTopAppBar(vegetable = vegetable) }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF82B64B))
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
                items(viewModel.filterSellers(sellers, vegetable)) { seller ->
                    SellerItem(seller = seller)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColoredTopAppBar(vegetable: String) {
    TopAppBar(
        title = {
            Text(
                text = "$vegetable (Aloo)",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF82B64B)
        )
    )
}

@Composable
fun CurvedImageBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(Color(0xFF82B64B))
            .padding(1.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.category_vegetable),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF82B64B))
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
fun SellerItem(seller: SellersDetailData) {
    Card(modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
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
                text = seller.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Quantity: ${seller.quantity}kg",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
            Text(
                text = "Distance: ${seller.distance}km",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "₹${seller.pricePerKg}/Kg",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column {
                Text(
                    text = "${seller.rating} out of 5",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${seller.reviewCount} customer ratings)",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
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
            Divider(
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

@Preview(showBackground = true)
@Composable
fun PreviewSellerDetailsScreen() {
    val sampleSellers = listOf(
        SellersDetailData("Brijesh Lal", "Potato", 2.0, 67, 15.0, 4.2, 12),
        SellersDetailData("Shyamu", "Potato", 2.1, 67, 17.0, 4.2, 12),
        SellersDetailData("Chotu", "Potato", 2.7, 43, 12.0, 4.2, 12),
        SellersDetailData("Brijesh Lal", "Potato", 2.0, 67, 15.0, 4.2, 12),
        SellersDetailData("Shyamu", "Potato", 2.1, 67, 17.0, 4.2, 12),
        SellersDetailData("Chotu", "Potato", 2.7, 43, 12.0, 4.2, 12)
    )

    // Provide a ViewModel instance if needed
    val viewModel = SellerDetailsViewModel()

    SellerDetailsScreen(
        sellers = sampleSellers,
        vegetable = "Potato",
        viewModel = viewModel
    )
}

