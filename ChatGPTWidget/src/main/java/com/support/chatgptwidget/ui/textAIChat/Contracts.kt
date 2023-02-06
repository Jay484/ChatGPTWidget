package com.support.chatgptwidget.ui.textAIChat

import com.support.chatgptwidget.network.models.responsemodels.Choice

class TextAiChatViewState

sealed class TextAiChatViewEvent{
    object LoadingCompletion: TextAiChatViewEvent()
    data class TextCompleted(
        val choices: List<Choice>
    ): TextAiChatViewEvent()
}

sealed class TextAiChatViewEffect{
    object ViewModelInitialized: TextAiChatViewEffect()
    data class LoadText(val text: String): TextAiChatViewEffect()
}