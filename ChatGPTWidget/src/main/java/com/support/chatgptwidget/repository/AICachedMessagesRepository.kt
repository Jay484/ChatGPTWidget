package com.support.chatgptwidget.repository

import com.support.chatgptwidget.models.AIChatImageMessage
import com.support.chatgptwidget.models.AIChatTextMessage

interface AICachedMessagesRepository {

    suspend fun getSavedTextMessages() : List<AIChatTextMessage>
    suspend fun getSavedImageMessages() : List<AIChatImageMessage>
}