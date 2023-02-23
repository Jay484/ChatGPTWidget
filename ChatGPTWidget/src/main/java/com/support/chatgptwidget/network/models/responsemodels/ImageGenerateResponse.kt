package com.support.chatgptwidget.network.models.responsemodels

data class ImageGenerateResponse(
    val created: Int,
    val data : List<ImageResponse>
)


data class ImageResponse(
    val url: String
)