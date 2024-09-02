package com.example.farmlinkapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp1.ui.auth.AuthScreen
import com.example.farmlinkapp1.ui.auth.AuthViewModel
import com.example.farmlinkapp1.ui.theme.FarmLinkAppTheme
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FarmLinkAppTheme {
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
                                    authViewModel.setLoading(false)
                                }
                            },
                            onError = { error ->
                                authViewModel.setLoading(false)
                                messageBarState.addError(error)
                            }
                        )
                        messageBarState.addSuccess("Successfully Authenticated")
                    },
                    onDialogDismissed = {
                        //authViewModel.setLoading(false)
                        messageBarState.addError(Exception(it))
                    }
                )
            }
        }
    }
}