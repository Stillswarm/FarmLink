package com.example.farmlinkapp1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.farmlinkapp1.common.AppScaffold
import com.example.farmlinkapp1.ui.for_buyer.sellers.SellersScreen
import com.example.farmlinkapp1.ui.for_seller.SellerDashboardScreen
import com.example.farmlinkapp1.ui.theme.FarmLinkAppTheme
import org.mongodb.kbson.ObjectId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FarmLinkAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.d("fuck", "activity")
                    FarmLinkApp()
//                    AppScaffold(currentScreenTitle = "Orange", onNavigateUp = { /*TODO*/ }, canNavigateUp = false) {
//                        SellerDashboardScreen(modifier = it)
//                    }
                }
            }
        }
    }
}