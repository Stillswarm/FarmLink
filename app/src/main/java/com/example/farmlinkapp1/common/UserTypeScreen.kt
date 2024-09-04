package com.example.farmlinkapp1.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmlinkapp1.R

@Composable
fun UserTypeScreen(
    onContinueAsSeller: () -> Unit,
    onContinueAsBuyer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.fl_logo),
                contentDescription = null
            )

            Spacer(Modifier.height(48.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                onClick = onContinueAsSeller,
                shape = RectangleShape
            ) {
                Text("Continue as a Seller", fontSize = 24.sp)
            }

            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                onClick = onContinueAsBuyer,
                shape = RectangleShape
            ) {
                Text("Continue as a Buyer", fontSize = 24.sp)
            }
        }
    }
}