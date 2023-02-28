package com.support.chatgptwidget.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "https://api.openai.com/v1/"

val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun getAuthInterceptor(API_TOKEN: String) : Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .build()
            return chain.proceed(request)
        }
    }
}

fun getOkHttpClient(API_TOKEN: String?): OkHttpClient{
    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(httpLoggingInterceptor)
        .apply {
            if(API_TOKEN != null)
                addInterceptor(getAuthInterceptor(API_TOKEN))
        }
        .build()
}

class RetrofitObject{
    companion object{
        private var aiChatRetrofitInstance : Retrofit? = null
        fun getAIChatRetrofitInstance(API_TOKEN: String?) : Retrofit{
            synchronized(this) {
                var temp = aiChatRetrofitInstance
                if (temp == null) {
                    temp = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .client(getOkHttpClient(API_TOKEN))
                        .build()
                    aiChatRetrofitInstance = temp
                }
                return temp!!
            }
        }
    }
}
class APIService {
    companion object{
        private var token : String? = null

        /***
         * @param API_TOKEN Default if empty string.
         * Pass the token if you want to change token at any point, else previous token will be
         * used for api calls.
         * Pass null if you want to remove auth header for further calls.
         */
        fun getChatGPTApiService(API_TOKEN: String? = "") : ChatGPTApiService {
            if(API_TOKEN != ""){
                token = API_TOKEN
            }

            return RetrofitObject.getAIChatRetrofitInstance(API_TOKEN)
                .create(ChatGPTApiService::class.java)
        }

        /***
         * @param API_TOKEN Manually set token for further api calls.
         * Pass null if you want to remove auth header for further calls.
         */
        @JvmStatic
        fun setAPIToken(API_TOKEN: String?){
            token = API_TOKEN
        }
    }
}