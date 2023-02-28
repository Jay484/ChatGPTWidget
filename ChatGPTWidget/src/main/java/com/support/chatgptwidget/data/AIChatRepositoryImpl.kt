package com.support.chatgptwidget.data

import com.support.chatgptwidget.data.network.ChatGPTApiService
import com.support.chatgptwidget.data.network.models.requestmodels.ImageGenerateRequest
import com.support.chatgptwidget.data.network.models.requestmodels.TextCompletionRequest
import com.support.chatgptwidget.repository.AIChatRepository
import com.support.chatgptwidget.ui.imageAIChat.ImageAIChatEvent
import com.support.chatgptwidget.ui.listmodels.ListModelViewEvent
import com.support.chatgptwidget.ui.textAIChat.TextAiChatViewEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge

class AIChatRepositoryImpl(private val chatGPTApiService: ChatGPTApiService) : AIChatRepository {
    override fun getAIModels(): Flow<ListModelViewEvent> {
        val loading = flowOf(ListModelViewEvent.LoadingModels)
        val data = flow {
            val resp = chatGPTApiService.getModels()
            if(resp.body() != null){
                emit(ListModelViewEvent.ModelsLoaded(resp.body()!!.data))
            }
        }
        return merge(loading,data)
    }

    override fun completeText(completionRequest: TextCompletionRequest): Flow<TextAiChatViewEvent> {
        val loading = flowOf(TextAiChatViewEvent.LoadingCompletion)
        val data = flow {
            val resp = chatGPTApiService.completeText(completionRequest)
            if (resp.body() != null)
                emit(TextAiChatViewEvent.TextCompleted(resp.body()!!.choices))
        }
        return merge(loading,data)
    }

    override fun generateImage(
        description: String,
        n: Int,
        size: String
    ) : Flow<ImageAIChatEvent> {
        val loading = flowOf(ImageAIChatEvent.GeneratingImage)
        val data = flow {
            val resp = chatGPTApiService.generateImages(
                ImageGenerateRequest(description, n, size)
            )
            if(resp.body() != null)
                emit(ImageAIChatEvent.ImagesGenerated(resp.body()!!.data))
        }

        return merge(loading, data)
    }
}