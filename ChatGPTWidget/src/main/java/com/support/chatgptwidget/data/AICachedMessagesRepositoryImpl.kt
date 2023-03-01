package com.support.chatgptwidget.data

import com.support.chatgptwidget.models.AIChatImageMessage
import com.support.chatgptwidget.models.AIChatTextMessage
import com.support.chatgptwidget.repository.AICachedMessagesRepository

class AICachedMessagesRepositoryImpl : AICachedMessagesRepository {
    override suspend fun getSavedTextMessages(): List<AIChatTextMessage> {
        return emptyList()
    }

    override suspend fun getSavedImageMessages(): List<AIChatImageMessage> {
        return emptyList()
    }
}