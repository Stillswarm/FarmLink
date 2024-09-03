package com.example.farmlinkapp1.navigation

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.farmlinkapp1.AppViewModel
import com.example.farmlinkapp1.common.AppScaffold
import com.example.farmlinkapp1.ui.home.HomeScreen
import com.example.farmlinkapp1.ui.items.ItemsScreen
import com.example.farmlinkapp1.ui.sellers.SellersScreen
import org.mongodb.kbson.ObjectId
import kotlin.reflect.typeOf

fun NavGraphBuilder.mainApp(navController: NavHostController) {
    Log.d("fuck", "mainapp")
    navigation<MainApp>(startDestination = Home) {
        home(navController)
        items(navController)
        sellerInventory(navController)
        sellerDetails(navController)
    }
}

fun NavGraphBuilder.home(navController: NavHostController) {
    composable<Home> {
        AppScaffold(
            currentScreenTitle = "FarmLink",
            canNavigateUp = false,
            onNavigateUp = { navController.popBackStack() },
        ) {

            Log.d("fuck", "home")

            HomeScreen(
                modifier = it,
                onClick = { categoryId ->
                    navController.navigate(Items(categoryId = categoryId))
                }
            )
        }
    }
}

fun NavGraphBuilder.items(navController: NavHostController) {
    composable<Items>(
        typeMap = mapOf(
            typeOf<ObjectId>() to CustomNavType.objectIdType
        )
    ) { backStackEntry ->
        val appViewModel: AppViewModel = viewModel()
        val item = backStackEntry.toRoute<Items>()
        AppScaffold(
            currentScreenTitle = appViewModel.getCategoryName(item.categoryId),
            onNavigateUp = { navController.popBackStack() }
        ) { modifier ->
            ItemsScreen(
                modifier = modifier,
                categoryId = item.categoryId,
                onClick = {
                    navController.navigate(SellerInventory(it))
                }
            )
        }
    }
}

fun NavGraphBuilder.sellerInventory(navController: NavHostController) {
    composable<SellerInventory>(
        typeMap = mapOf(
            typeOf<ObjectId>() to CustomNavType.objectIdType
        )
    ) { backStackEntry ->
        val item = backStackEntry.toRoute<SellerInventory>()
        val appViewModel: AppViewModel = viewModel()
        AppScaffold(
            currentScreenTitle = appViewModel.getItemName(item.itemId),
            onNavigateUp = { navController.popBackStack() }
        ) { modifier ->
            SellersScreen(modifier = modifier, itemId = item.itemId)
        }
    }
}

fun NavGraphBuilder.sellerDetails(navController: NavHostController) {
    composable<SellerDetails> {
    }
}

fun NavGraphBuilder.chat() {
    composable<Chat> {
    }
}