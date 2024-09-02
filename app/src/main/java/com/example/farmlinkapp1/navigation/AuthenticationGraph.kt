package com.example.farmlinkapp1.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.farmlinkapp1.ui.auth.AuthScreen
import com.example.farmlinkapp1.ui.auth.AuthViewModel
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState

fun NavGraphBuilder.authentication(navController: NavHostController) {

    navigation<Authentication>(startDestination = SignIn) {
        signIn(navController)
        userType()
        userDetails()
    }
}

fun NavGraphBuilder.signIn(navController: NavHostController) {
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
                            navController.navigate(MainApp)
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
    }
}

fun NavGraphBuilder.userType() {
    composable<UserType> {

    }
}

fun NavGraphBuilder.userDetails() {
    composable<UserDetails> {

    }
}