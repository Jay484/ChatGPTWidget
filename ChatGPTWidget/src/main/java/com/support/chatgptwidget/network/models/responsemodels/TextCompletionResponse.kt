package com.support.chatgptwidget.network.models.responsemodels

data class TextCompletionResponse(
    val id: String,
    val choices: List<Choice>
)

data class Choice(
    val text: String,
    val index: Int
)