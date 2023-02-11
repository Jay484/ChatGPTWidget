package com.support.chatgptwidget.network.models.requestmodels

import com.google.gson.annotations.SerializedName

data class TextCompletionRequest(
    val model: String,
    @SerializedName("prompt")
    val text: String,
    @SerializedName("max_tokens")
    @androidx.annotation.IntRange(0,4096)
    val maxWordsCount: Int
)