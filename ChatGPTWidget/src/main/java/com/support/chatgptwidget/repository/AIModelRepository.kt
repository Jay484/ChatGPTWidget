package com.support.chatgptwidget.repository

import com.support.chatgptwidget.models.ChatAIModel
import kotlinx.coroutines.flow.Flow

interface AIModelRepository {
    fun getAIModels(): Flow<List<ChatAIModel>>
}