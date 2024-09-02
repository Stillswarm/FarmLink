package com.example.farmlinkapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp1.navigation.NavGraphSetup
import com.example.farmlinkapp1.ui.auth.AuthScreen
import com.example.farmlinkapp1.ui.auth.AuthViewModel
import com.example.farmlinkapp1.ui.theme.FarmLinkAppTheme
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FarmLinkAppTheme {
                FarmLinkApp()
            }
        }
    }
}