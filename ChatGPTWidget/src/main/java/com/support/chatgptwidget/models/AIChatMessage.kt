package com.support.chatgptwidget.models

data class AIChatMessage(
    val sender: Sender,
    val text: String
)

enum class Sender(val key:String, val type: Int){
    Bot("bot", 1),
    Me("me", 0)
}