package com.example.farmlinkapp1.ui.for_buyer.seller_details

import android.content.Intent
import android.graphics.Paint.Style
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.farmlinkapp1.model.Review
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerDetailsScreen(viewModel: SellerDetailsViewModel = viewModel()) {
    val seller by viewModel.seller.collectAsState()
    val user by viewModel.user.collectAsState()
    val item by viewModel.item.collectAsState()
    val saleItem by viewModel.saleItem.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
//                colors = TopAppBarDefaults.mediumTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary
//                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Potato",
                            style = MaterialTheme.typography.headlineMedium)//${saleItem.item}
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Card(
                    modifier = Modifier.padding(top = 20.dp), elevation = CardDefaults.cardElevation(8.dp), // Set the elevation here
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    ) {
                        // Vendor Image and Name
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(user.picture),
                                contentDescription = "Vendor Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Brijesh Lal",
                                    style = MaterialTheme.typography.displaySmall
                                )
                                Rating(rating = 4.0f, maxRating = 5.0f)
                            }
                        }

                        // Vendor Information
                        Column(
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Row {
                                Column {
                                    Text(
                                        text = "Quantity: ${saleItem.item} kg",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "Distance: ${saleItem.distance} km",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                                Spacer(modifier = Modifier.width(30.dp))
                                Card(modifier = Modifier.padding(horizontal = 10.dp,
                                    vertical = 5.dp),
                                    elevation = CardDefaults.cardElevation(4.dp), // Set the elevation here
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors( // Set background color of the card
                                        containerColor = MaterialTheme.colorScheme.primaryContainer)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Box(modifier = Modifier.padding(horizontal = 4.dp),
                                            contentAlignment = Alignment.Center){
                                            Text(
                                                text = "Current Price",
                                                style = MaterialTheme.typography.titleSmall,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(2.dp)
                                            )
                                        }
                                        Box(modifier = Modifier.padding(horizontal = 12.dp),
                                            contentAlignment = Alignment.Center){
                                            Text(
                                                text = "₹15/kg",
                                                style = MaterialTheme.typography.titleLarge,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        }

                                    }
                                }
                            }

                            Text(
                                text = "Address: Brijesh Lal, Dipatoli Mandi, Khelgaon, Ranchi (834010)",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        // Reviews section
                        Text(
                            text = "Reviews:",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Scrollable list of reviews
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight(0.8f) // Limit the height for scrolling reviews
                                .padding(bottom = 16.dp)
                        ) {
                            items(4) {
                                Review(
                                    name = "Sunny Bhaiya",
                                    rating = 0.0f,
                                    reviewText = "Bhot kharab hai inki (sabjiya), bhot kadad hai inki (bhindi), inki sunghate hi ulti ho gyi itni gandi hai inki (sabji)."
                                )
                                Review(
                                    name = "Surya Bhaiya",
                                    rating = 7.0f,
                                    reviewText = "Bhot hi sungandhit hai inki (sabjiya), jee kar karta hai din bhar chatata rahu inke tamatar ki chatni."
                                )
                            }
                        }

                        // Contact Buttons fixed at the bottom
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 10.dp), // Add bottom padding of 30dp
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = { /* Handle Message Action */ },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MailOutline,
                                    contentDescription = "Message"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Message")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_CALL).apply {
                                        data = Uri.parse("tel:${user.phoneNumber}")
                                    }
//                                    val context = LocalContext.current
//                                    val intent = Intent(Intent.ACTION_CALL).apply {
//                                        data = Uri.parse("tel:${user.phoneNumber}")
                                    //}
//                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "Call"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Call")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}

@Composable
fun Rating(rating: Float, maxRating: Float) {
    Row {
        for (i in 1..maxRating.toInt()) {
            if (i <= rating.toInt()) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled Star",
                    tint = MaterialTheme.colorScheme.primary
                )
            } else if (i - 1 < rating) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Half Filled Star",
                    tint = MaterialTheme.colorScheme.primary
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Empty Star",
                    tint = Color(0xFF9E9E9E)
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
}

@Composable
fun Review(name: String, rating: Float, reviewText: String) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "$name (${rating.toInt()} out of 5 star ratings)",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = reviewText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SellerDetailsScreenPreview() {
    SellerDetailsScreen()
}
