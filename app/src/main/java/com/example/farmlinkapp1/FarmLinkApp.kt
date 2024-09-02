package com.example.farmlinkapp1

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.farmlinkapp1.navigation.Chat
import com.example.farmlinkapp1.navigation.CustomNavType
import com.example.farmlinkapp1.navigation.Home
import com.example.farmlinkapp1.navigation.Items
import com.example.farmlinkapp1.navigation.MainApp
import com.example.farmlinkapp1.navigation.Authentication
import com.example.farmlinkapp1.navigation.SellerDetails
import com.example.farmlinkapp1.navigation.SellerInventory
import com.example.farmlinkapp1.navigation.SignIn
import com.example.farmlinkapp1.navigation.UserDetails
import com.example.farmlinkapp1.navigation.UserType
import com.example.farmlinkapp1.ui.auth.AuthScreen
import com.example.farmlinkapp1.ui.auth.AuthViewModel
import com.example.farmlinkapp1.ui.home.HomeScreen
import com.example.farmlinkapp1.ui.items.ItemsScreen
import com.example.farmlinkapp1.ui.sellers.SellersScreen
import com.example.farmlinkapp1.util.Constants.APP_ID
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import org.mongodb.kbson.ObjectId
import kotlin.reflect.typeOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmLinkApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    val appViewModel: AppViewModel = hiltViewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var atHomeScreen by remember {
        mutableStateOf(true)
    }

    var currentScreenTitle by remember {
        mutableStateOf("FarmLink")
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = currentScreenTitle,
                scrollBehavior = scrollBehavior,
                onNavigateUp = { navController.navigateUp() },
                canNavigateUp = !atHomeScreen
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NavHost(
            startDestination = getStartDestination(),
            navController = navController,
            modifier = modifier
                .padding(innerPadding)
                .padding(top = 16.dp)
        ) {
            navigation<Authentication>(startDestination = SignIn) {

                composable<SignIn> {
                    val authViewModel: AuthViewModel = viewModel()
                    val messageBarState = rememberMessageBarState()
                    val oneTapSignInState = rememberOneTapSignInState()
                    AuthScreen(
                        loadingState = oneTapSignInState.opened,
                        onButtonClick = {
                            authViewModel.setLoading(true)
                            oneTapSignInState.open()

                        },
                        oneTapState = oneTapSignInState,
                        messageBarState = messageBarState,
                        onTokenIdReceived = { tokenId ->
                            authViewModel.signInWithMongoAtlas(
                                tokenId = tokenId,
                                onSuccess = {
                                    if (it) {
                                        messageBarState.addSuccess("Successfully Authenticated")
                                    }
                                },
                                onError = { error ->
                                    messageBarState.addError(error)
                                }
                            )
                            authViewModel.setLoading(false)
                        },
                        onDialogDismissed = {
                            authViewModel.setLoading(false)
                            messageBarState.addError(Exception(it))
                        }
                    )
                    navController.navigate(MainApp)
                    currentScreenTitle = "Sign In"
                }

                composable<UserType> {

                }

                composable<UserDetails> {

                }
            }

            navigation<MainApp>(startDestination = Home) {
                composable<Home> {
                    atHomeScreen = true
                    currentScreenTitle = "FarmLink"
                    HomeScreen(
                        onClick = { categoryId ->
                            navController.navigate(Items(categoryId = categoryId))
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
                    ItemsScreen(
                        item.categoryId, onClick = {
                            navController.navigate(SellerInventory(it))
                        }
                    )
                    currentScreenTitle = appViewModel.getCategoryName(item.categoryId)
                }

                composable<SellerInventory>(
                    typeMap = mapOf(
                        typeOf<ObjectId>() to CustomNavType.objectIdType
                    )
                ) { backStackEntry ->
                    val item = backStackEntry.toRoute<SellerInventory>()
                    atHomeScreen = false
                    SellersScreen(item.itemId)
                    currentScreenTitle = appViewModel.getItemName(item.itemId)
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

private fun getStartDestination() : Any {
    val user = App.create(APP_ID).currentUser

    if (user != null && user.loggedIn) return MainApp
    else return Authentication
}