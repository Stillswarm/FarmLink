package com.example.farmlinkapp1.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmlinkapp1.R
import com.example.farmlinkapp1.data.MongoDB
import kotlinx.coroutines.launch

@Composable
fun UserDetailsScreen(
    navigateToUserType: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var address by remember {
        mutableStateOf("")
    }

    var phoneNo by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.fl_logo),
            contentDescription = null
        )

        Text(
            text = "Please fill in these details to continue",
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = {
                Text(
                    text = "Your Address",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        OutlinedTextField(
            value = phoneNo,
            onValueChange = { phoneNo = it },
            label = {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Button(onClick = {
            scope.launch {
                MongoDB.createUser(address, phoneNo)
            }
            navigateToUserType()
        }) {
            Text(text = "Continue")
        }
    }
}