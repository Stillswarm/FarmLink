package com.example.farmlinkapp1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.farmlinkapp1.common.AppScaffold
import org.mongodb.kbson.ObjectId
import kotlin.reflect.typeOf

fun NavGraphBuilder.mainApp() {
    navigation<MainApp>(startDestination = Home) {
        home()
        items()
        sellerInventory()
        sellerDetails()
    }
}

fun NavGraphBuilder.home() {
    composable<Home> {
        AppScaffold(
            currentScreenTitle = "FarmLink",
            canNavigateUp = false,
            onNavigateUp = { /*TODO*/ },
        ) {
//            HomeScreen(
//                onClick = { categoryId ->
//                    navController.navigate(Items(categoryId = categoryId))
//                }
//            )
        }
    }
}

fun NavGraphBuilder.items() {
    composable<Items>(
        typeMap = mapOf(
            typeOf<ObjectId>() to CustomNavType.objectIdType
        )
    ) { backStackEntry ->
        val item = backStackEntry.toRoute<Items>()
        AppScaffold(
            currentScreenTitle = "Items",
            onNavigateUp = {}
        ) {
//            ItemsScreen(
//                        item.categoryId, onClick = {
//                            navController.navigate(SellerInventory(it))
//                        }
//                    )
        }
//
        //currentScreenTitle = appViewModel.getCategoryName(item.categoryId)
    }
}

fun NavGraphBuilder.sellerInventory() {
    composable<SellerInventory>(
        typeMap = mapOf(
            typeOf<ObjectId>() to CustomNavType.objectIdType
        )
    ) { backStackEntry ->
        val item = backStackEntry.toRoute<SellerInventory>()
                //SellersScreen(item.itemId)
        //currentScreenTitle = appViewModel.getItemName(item.itemId)
    }
}

fun NavGraphBuilder.sellerDetails() {
    composable<SellerDetails> {
    }
}

fun NavGraphBuilder.chat() {
    composable<Chat> {
    }
}