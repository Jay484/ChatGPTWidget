package com.support.chatgptwidget.data.network

import com.support.chatgptwidget.data.network.models.requestmodels.ImageGenerateRequest
import com.support.chatgptwidget.data.network.models.requestmodels.TextCompletionRequest
import com.support.chatgptwidget.data.network.models.responsemodels.BaseResponseModel
import com.support.chatgptwidget.data.network.models.responsemodels.ChatAIModel
import com.support.chatgptwidget.data.network.models.responsemodels.ImageGenerateResponse
import com.support.chatgptwidget.data.network.models.responsemodels.TextCompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatGPTApiService {
    @GET("models")
    suspend fun getModels() : Response<BaseResponseModel<List<ChatAIModel>>>

    @POST("completions")
    suspend fun completeText(
       @Body textCompletionRequest: TextCompletionRequest
    ): Response<TextCompletionResponse>

    @POST("images/generations")
    suspend fun generateImages(
        @Body imageGenerateRequest: ImageGenerateRequest
    ) : Response<ImageGenerateResponse>
}