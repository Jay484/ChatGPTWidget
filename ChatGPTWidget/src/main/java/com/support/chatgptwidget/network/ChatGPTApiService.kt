package com.support.chatgptwidget.network

import com.support.chatgptwidget.models.BaseResponseModel
import com.support.chatgptwidget.models.ChatAIModel
import retrofit2.Response
import retrofit2.http.GET

interface ChatGPTApiService {
    @GET("models")
    suspend fun getModels() : Response<BaseResponseModel<List<ChatAIModel>>>
}