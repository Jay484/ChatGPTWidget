package com.support.chatgptwidget.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import com.support.chatgptwidget.models.AIChatTextMessage

@Entity(tableName = "TextMessage")
class TextMessageEntity (
    val sender: String,
    val message: String
){
    @Ignore
    constructor(
        aiChatTextMessage: AIChatTextMessage
    ) : this(aiChatTextMessage.sender.key, aiChatTextMessage.text)
}