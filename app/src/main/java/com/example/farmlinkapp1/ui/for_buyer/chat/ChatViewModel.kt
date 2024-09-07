package com.example.farmlinkapp1.ui.for_buyer.chat

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.farmlinkapp1.data.MongoDB.realm
import com.example.farmlinkapp1.model.ChatMessage
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.DeletedObject
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val realm: Realm = Realm.open()

    val messages = mutableStateListOf<ChatMessage>()

    init {
        observeChatMessages()
    }

    fun sendMessage(messageText: String, senderId: String, chatRoomId: String) {
        val message = ChatMessage().apply {
            message = messageText
            this.senderId = senderId
            this.chatRoomId = chatRoomId
            timestamp = System.currentTimeMillis()
        }
        realm.writeBlocking {
            copyToRealm(message)
        }
    }

    private fun observeChatMessages() {
        viewModelScope.launch {
            realm.query<ChatMessage>("chatRoomId = $0", "12345").asFlow().collect { result ->
                when (result) {
                    is InitialResults -> {
                        // Load initial messages
                        messages.clear()
                        messages.addAll(result.list)
                    }
                    is UpdatedResults -> {
                        // Process valid messages, skipping deleted objects
                        result.list.forEach { updatedObject ->
                            if (updatedObject !is DeletedObject<*>) {
                                val chatMessage = updatedObject as? ChatMessage
                                chatMessage?.let { messages.add(it) }
                            }
                        }
                    }
                }
            }
        }
    }



    override fun onCleared() {
        realm.close() // Close the Realm instance when ViewModel is cleared
        super.onCleared()
    }
}
