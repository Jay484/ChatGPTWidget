package com.support.chatgptwidget.ui.imageAIChat

import com.support.chatgptwidget.network.models.responsemodels.ImageResponse

sealed class ImageAIChatEvent{
    object GeneratingImage : ImageAIChatEvent()
    data class ImagesGenerated(val images:List<ImageResponse>) : ImageAIChatEvent()
}