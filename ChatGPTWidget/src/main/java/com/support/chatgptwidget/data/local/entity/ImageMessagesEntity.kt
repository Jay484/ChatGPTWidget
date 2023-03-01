package com.support.chatgptwidget.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import com.support.chatgptwidget.models.AIChatImageMessage
import com.support.chatgptwidget.models.Sender

@Entity(tableName = "ImageMessages")
class ImageMessagesEntity(
    val sender: String,
    val imageUrl: String?,
    val description: String?
) {
    @Ignore
    constructor(
        aiChatImageMessage: AIChatImageMessage
    ) : this(
        aiChatImageMessage.sender.key,
        aiChatImageMessage.imgUrl,
        aiChatImageMessage.description
    )
}



fun ImageMessagesEntity.toAiChatImageMessage(): AIChatImageMessage {
    return AIChatImageMessage(
        when (this.sender) {
            Sender.Bot.key -> {
                Sender.Bot
            }
            else -> {
                Sender.Me
            }
        },
        this.imageUrl,
        this.description
    )
}