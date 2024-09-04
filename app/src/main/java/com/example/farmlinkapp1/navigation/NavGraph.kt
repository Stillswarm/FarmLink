package com.example.farmlinkapp1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.farmlinkapp1.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App

@Composable
fun NavGraphSetup(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = Authentication,
        navController = navController,
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        authentication(navController)
        buyerApp(navController)
        sellerApp(navController)
    }
}

private fun getStartDestination(): Any {
    val user = App.create(APP_ID).currentUser

    return if (user != null && user.loggedIn) UserType
    else Authentication
}