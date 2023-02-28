package com.support.chatgptwidget.data.network.models.requestmodels

data class ImageGenerateRequest (
    val prompt: String,
    val n: Int,
    val size: String
)