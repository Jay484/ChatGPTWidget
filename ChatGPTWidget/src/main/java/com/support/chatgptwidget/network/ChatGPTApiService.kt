package com.support.chatgptwidget.network

import com.support.chatgptwidget.models.ChatGPTModel
import retrofit2.http.GET

interface ChatGPTApiService {
    @GET("models")
    suspend fun getModels() : BaseResponseModel<List<ChatGPTModel>>
}