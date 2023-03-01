package com.support.chatgptwidget.data.network.models.responsemodels

import com.google.gson.annotations.SerializedName

data class BaseResponseModel<T>(
    val data : T,
    @SerializedName("object")
    val objectType: String
)