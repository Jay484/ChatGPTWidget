package com.support.chatgptwidget.models

data class AIChatImageMessage(
    val sender: Sender,
    val imgUrl: String?,
    val description: String?
)