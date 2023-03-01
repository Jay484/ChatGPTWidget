package com.support.chatgptwidget.ui.textAIChat

import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIChatRepositoryImpl
import com.support.chatgptwidget.models.AIChatTextMessage
import com.support.chatgptwidget.models.Sender
import com.support.chatgptwidget.data.network.APIService
import com.support.chatgptwidget.data.network.models.requestmodels.TextCompletionRequest
import com.support.chatgptwidget.ui.textAIChat.TextAiChatViewEffect.ViewModelInitialized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TextAiChatViewModel : ViewModel() {
    var viewEffect = MutableStateFlow<TextAiChatViewEffect>(ViewModelInitialized)
    var apiToken: String? = null
    var model = "text-davinci-003"
    val data = arrayListOf(
        AIChatTextMessage(Sender.Bot, "How may I help you"),
    )
    fun completeText(text: String){
        data.add(AIChatTextMessage(Sender.Me,text))
        CoroutineScope(Dispatchers.IO).launch {
            AIChatRepositoryImpl(
                APIService.getChatGPTApiService(apiToken)
            ).completeText(
                TextCompletionRequest(
                model,
                text,
                1024
            )
            ).collectLatest {
                processEvent(it)
            }
        }
    }

    private suspend fun processEvent(event: TextAiChatViewEvent){
        when(event){
            TextAiChatViewEvent.LoadingCompletion ->{

            }
            is TextAiChatViewEvent.TextCompleted ->{
                val message = AIChatTextMessage(Sender.Bot, event.choices[0].text.trim())
                data.add(message)
                viewEffect.emit(
                    TextAiChatViewEffect.LoadMessage(message)
                )
            }
        }
    }
}