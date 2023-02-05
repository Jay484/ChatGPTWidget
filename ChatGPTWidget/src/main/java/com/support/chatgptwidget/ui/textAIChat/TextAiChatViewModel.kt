package com.support.chatgptwidget.ui.textAIChat

import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIModelRepositoryImpl
import com.support.chatgptwidget.network.APIService
import com.support.chatgptwidget.network.models.requestmodels.TextCompletionRequest
import com.support.chatgptwidget.ui.textAIChat.TextAiChatViewEffect.ViewModelInitialized
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TextAiChatViewModel : ViewModel() {
    var viewState: TextAiChatViewState = TextAiChatViewState()
    var viewEffect: MutableStateFlow<TextAiChatViewEffect> = MutableStateFlow(ViewModelInitialized)
    var apiToken: String? = null
    fun completeText(text: String){
        CoroutineScope(Dispatchers.IO).launch {
            AIModelRepositoryImpl(
                APIService.getChatGPTApiService(apiToken)
            ).completeText(TextCompletionRequest(
                "text-davinci-003",
                text,
                30
            )).collectLatest {
                processEvent(it)
            }
        }
    }

    private suspend fun processEvent(event: TextAiChatViewEvent){
        when(event){
            TextAiChatViewEvent.LoadingCompletion ->{

            }
            is TextAiChatViewEvent.TextCompleted ->{
                viewEffect.emit(TextAiChatViewEffect.LoadText(event.choices[0].text))
            }
        }
    }
}