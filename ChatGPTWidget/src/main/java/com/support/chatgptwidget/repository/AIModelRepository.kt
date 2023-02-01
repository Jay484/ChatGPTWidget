package com.support.chatgptwidget.repository

import com.support.chatgptwidget.ui.listmodels.ListModelViewEvent
import kotlinx.coroutines.flow.Flow

interface AIModelRepository {
    fun getAIModels(): Flow<ListModelViewEvent>
}