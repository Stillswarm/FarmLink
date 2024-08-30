package com.example.farmlinkapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.farmlinkapp.navigation.Chat
import com.example.farmlinkapp.navigation.CustomNavType
import com.example.farmlinkapp.navigation.Home
import com.example.farmlinkapp.navigation.Items
import com.example.farmlinkapp.navigation.MainApp
import com.example.farmlinkapp.navigation.Register
import com.example.farmlinkapp.navigation.SellerDetails
import com.example.farmlinkapp.navigation.SellerInventory
import com.example.farmlinkapp.ui.home.HomeScreen
import com.example.farmlinkapp.ui.items.ItemsScreen
import org.mongodb.kbson.ObjectId
import kotlin.reflect.typeOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmLinkApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val appViewModel = hiltViewModel<AppViewModel>()

    var atHomeScreen by remember {
        mutableStateOf(true)
    }

    var screenTitle by remember {
        mutableStateOf("Farm Link")
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = screenTitle,
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
                            screenTitle = appViewModel.getCurrentScreenTitleByCategory(categoryId)
                        }
                    )
                }

                composable<Items>(
                    typeMap = mapOf(
                        typeOf<ObjectId>() to CustomNavType.objectIdType
                    )
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
    title: String,
    onNavigateUp: () -> Unit,
    canNavigateUp: Boolean,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
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
