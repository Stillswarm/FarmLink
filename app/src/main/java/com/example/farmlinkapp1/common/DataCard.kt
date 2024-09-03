package com.example.farmlinkapp1.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DataCard(
    title: String,
    imageUrl: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onCardClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AsyncImageLoader(imageUrl, title)

            //if (imageLoadSuccessful) {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black)
                            )
                        )
                )

                Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.BottomCenter) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
//}

@Preview(showSystemUi = true)
@Composable
fun DataCardPreview(modifier: Modifier = Modifier) {
    DataCard("VEGETABLES", "", {})
}