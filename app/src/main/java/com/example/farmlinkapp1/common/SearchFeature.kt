package com.example.farmlinkapp1.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.farmlinkapp1.R
import com.example.farmlinkapp1.common.VoiceTypingFeature

@Composable
fun SearchFeature(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var result by remember { mutableStateOf("") }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = textFieldValue,
        onValueChange = onValueChange,
        shape = CircleShape,
        placeholder = { Text("Search...") },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if (textFieldValue.isNotEmpty()) {
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Clear"
                    )
                }
            } else {
                IconButton(onClick = {
//                    VoiceTypingFeature { spokenText ->
//                        result = spokenText
//                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.filled_mic),
                        contentDescription = "Search"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SearchFeaturePreview() {
    SearchFeature("", {})
}