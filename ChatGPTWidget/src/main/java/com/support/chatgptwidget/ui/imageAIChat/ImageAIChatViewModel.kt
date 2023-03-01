package com.support.chatgptwidget.ui.imageAIChat

import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIChatRepositoryImpl
import com.support.chatgptwidget.models.AIChatImageMessage
import com.support.chatgptwidget.models.Sender
import com.support.chatgptwidget.data.network.APIService
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
    val data = arrayListOf<AIChatImageMessage>()

    fun getImages(description: String){
        data.add(AIChatImageMessage(Sender.Me, null, description))
        CoroutineScope(Dispatchers.IO).launch {
            AIChatRepositoryImpl(
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
                val message = AIChatImageMessage(Sender.Bot, event.images[0].url, null)
                data.add(message)
                viewEffect.emit(
                    LoadImage(event.images[0].url)
                )
            }
        }
    }

}