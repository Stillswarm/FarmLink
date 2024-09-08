package com.example.farmlinkapp1.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.farmlinkapp1.data.MongoDB
import com.example.farmlinkapp1.model.SaleItem
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun SaleItemCard(
    saleItem: SaleItem,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    cardColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    isRecommended: Boolean = false
) {
    Box {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            border = BorderStroke(0.2.dp, MaterialTheme.colorScheme.secondaryContainer),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.shadow(8.dp, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Quantity: ${saleItem.quantityInKg}kg",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Distance: ${
                            getDistance(
                                lat1 = saleItem.seller?.user?.latitude,
                                long1 = saleItem.seller?.user?.longitude,
                                lat2 = MongoDB.getCurrentUser().latitude,
                                long2 = MongoDB.getCurrentUser().longitude
                            )
                        }km",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Text(
                        text = "₹${saleItem.pricePerKg}/Kg",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (isRecommended) {
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Text(
                                text = "Recommended",
                                style = MaterialTheme.typography.labelSmall.copy(color = Color.Yellow)
                            )
                        }
                    }

                    //RatingStars(ratings = saleItem.reviews, size = 16.dp)

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

private fun getDistance(lat1: Double?, long1: Double?, lat2: Double, long2: Double): Double {
    if (lat1 == null || long1 == null) return 0.0
    val earthRadius = 6371 // Radius of the earth in km
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(long2 - long1)
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    val distance = earthRadius * c
    return distance
}