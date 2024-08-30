package com.example.farmlinkapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.farmlinkapp.common.SearchFeature
import com.example.farmlinkapp.navigation.*
import com.example.farmlinkapp.ui.home.HomeScreen
import com.example.farmlinkapp.ui.items.ItemsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmLinkApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    var value by remember {
        mutableStateOf("")
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var atHomeScreen by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                {
                    SearchFeature(
                        textFieldValue = value,
                        onValueChange = { value = it }
                    )
                },
                scrollBehavior = scrollBehavior,
                onNavigateUp = { navController.navigateUp() },
                canNavigateUp = !atHomeScreen
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NavHost(
            startDestination = MainApp,
            navController = navController,
            modifier = modifier
                .padding(innerPadding)
                .padding(top = 16.dp)
        ) {
            composable<Register> {
                navController.navigate(Home)
            }

            navigation<MainApp>(startDestination = Home) {
                composable<Home> {
                    atHomeScreen = true
                    HomeScreen(
                        onClick = { categoryId ->
                            navController.navigate(Items(categoryId = categoryId))
                        }
                    )
                }

                composable<Items>(
//                    typeMap = mapOf(
//                        typeOf<Category>() to CustomNavType.categoryType
//                    )
                ) { backStackEntry ->
                    val item = backStackEntry.toRoute<Items>()
                    atHomeScreen = false
                    ItemsScreen(item.categoryId)
                }

                composable<SellerInventory> {
                    atHomeScreen = false
                }

                composable<SellerDetails> {
                    atHomeScreen = false
                }

                composable<Chat> {
                    atHomeScreen = false
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    searchFeature: @Composable () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateUp: Boolean,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Column {
                searchFeature()
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            } else {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Open Navigation Drawer"
                    )
                }
            }
        }
    )
}
