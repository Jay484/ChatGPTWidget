package com.support.chatgptwidget.ui.imageAIChat

import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIModelRepositoryImpl
import com.support.chatgptwidget.network.APIService
import com.support.chatgptwidget.ui.imageAIChat.ImageAIChatViewEffect.ImageAIChatInitialized
import com.support.chatgptwidget.ui.imageAIChat.ImageAIChatViewEffect.LoadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageAIChatViewModel : ViewModel() {
    var apiToken : String? = null
    var viewEffect = MutableStateFlow<ImageAIChatViewEffect>(ImageAIChatInitialized)

    fun getImages(description: String){
        CoroutineScope(Dispatchers.IO).launch {
            AIModelRepositoryImpl(
                APIService.getChatGPTApiService(apiToken)
            ).generateImage(
                description
            ).collectLatest {
                processEvent(it)
            }
        }
    }

    private suspend fun processEvent(event: ImageAIChatEvent){
        when(event){
            ImageAIChatEvent.GeneratingImage -> {}
            is ImageAIChatEvent.ImagesGenerated -> {
                viewEffect.emit(
                    LoadImage(event.images[0].url)
                )
            }
        }
    }

}