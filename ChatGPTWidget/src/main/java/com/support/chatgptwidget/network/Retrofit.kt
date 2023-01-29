package com.support.chatgptwidget.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "https://api.openai.com/v1/"
const val API_TOKEN = "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"

val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

val authInterceptor = object: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_TOKEN")
            .build()
        return chain.proceed(request)
    }

}

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .addInterceptor(httpLoggingInterceptor)
    .addInterceptor(authInterceptor)
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()

val chatGPTApiService: ChatGPTApiService = retrofit.create(ChatGPTApiService::class.java)