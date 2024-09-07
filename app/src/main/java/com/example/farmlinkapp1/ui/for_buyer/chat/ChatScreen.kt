package com.example.farmlinkapp1.ui.for_buyer.chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmlinkapp1.model.ChatMessage

@Composable
fun ChatScreen(chatViewModel: ChatViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFECE5DD)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // List of messages
        LazyColumn(
            modifier = Modifier.weight(1f).padding(8.dp)
        ) {
            items(chatViewModel.messages.size) { index ->
                val message = chatViewModel.messages[index]
                ChatBubble(message)
            }
        }

        // Message input and send button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var text by remember { mutableStateOf("") }
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(8.dp)
            )
            IconButton(onClick = {
                if (text.isNotBlank()) {
                    chatViewModel.sendMessage(text, "currentUserId", "12345")
                    text = ""
                }
            }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val backgroundColor = if (message.senderId == "currentUserId") Color(0xFFDCF8C6) else Color.White
    val alignment = if (message.senderId == "currentUserId") Alignment.End else Alignment.Start

    Box(
        //contentAlignment = alignment,
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {
        Surface(
            color = backgroundColor,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.message,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
