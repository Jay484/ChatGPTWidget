package com.support.chatgptwidget.data.network.models.responsemodels

data class ImageGenerateResponse(
    val created: Int,
    val data : List<ImageResponse>
)


data class ImageResponse(
    val url: String
)