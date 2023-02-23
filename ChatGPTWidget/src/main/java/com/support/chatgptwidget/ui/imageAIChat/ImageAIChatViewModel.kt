package com.support.chatgptwidget.ui.imageAIChat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIModelRepositoryImpl
import com.support.chatgptwidget.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageAIChatViewModel : ViewModel() {
    var apiToken : String? = null

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

    private fun processEvent(event: ImageAIChatEvent){
        when(event){
            ImageAIChatEvent.GeneratingImage -> {}
            is ImageAIChatEvent.ImagesGenerated -> {
                Log.e("testJay", event.images.toString())
            }
        }
    }

}