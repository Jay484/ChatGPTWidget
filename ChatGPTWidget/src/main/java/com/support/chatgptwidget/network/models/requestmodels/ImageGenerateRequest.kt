package com.support.chatgptwidget.network.models.requestmodels

data class ImageGenerateRequest (
    val prompt: String,
    val n: Int,
    val size: String
)