package com.support.chatgptwidget.models

import com.google.gson.annotations.SerializedName

data class BaseResponseModel<T>(
    val data : T,
    @SerializedName("object")
    val objectType: String
)