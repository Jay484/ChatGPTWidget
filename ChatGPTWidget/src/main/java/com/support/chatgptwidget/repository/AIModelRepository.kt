package com.support.chatgptwidget.repository

import com.support.chatgptwidget.network.models.requestmodels.TextCompletionRequest
import com.support.chatgptwidget.ui.listmodels.ListModelViewEvent
import com.support.chatgptwidget.ui.textAIChat.TextAiChatViewEvent
import kotlinx.coroutines.flow.Flow

interface AIModelRepository {
    fun getAIModels(): Flow<ListModelViewEvent>
    fun completeText(completionRequest: TextCompletionRequest): Flow<TextAiChatViewEvent>
}