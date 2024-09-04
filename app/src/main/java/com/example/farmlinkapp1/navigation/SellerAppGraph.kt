package com.example.farmlinkapp1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.farmlinkapp1.common.AppScaffold
import com.example.farmlinkapp1.ui.for_seller.SellerDashboardScreen

fun NavGraphBuilder.sellerApp(navController: NavHostController) {
    navigation<SellerApp>(startDestination = SellerDashboard) {
        sellerDashboard()
        soldItemsScreen(navController)
        activeItemsScreen(navController)
    }
}

fun NavGraphBuilder.sellerDashboard() {
    composable<SellerDashboard> {
        AppScaffold(
            currentScreenTitle = "Farm Link",
            onNavigateUp = {},
            canNavigateUp = false
        ) {
            SellerDashboardScreen(modifier = it)
        }
    }
}

fun NavGraphBuilder.soldItemsScreen(navController: NavHostController) {
    composable<SoldItems> {

    }
}

fun NavGraphBuilder.activeItemsScreen(navController: NavHostController) {
    composable<ActiveItems> {

    }
}
