package com.support.chatgptwidget.ui.listmodels

import com.support.chatgptwidget.models.ChatAIModel
import kotlinx.coroutines.flow.Flow

data class ListModelsViewState(
    val chatAIModels : Flow<List<ChatAIModel>>
)

sealed class ListModelViewEvent{

    object LoadingModels : ListModelViewEvent()
    data class ModelsLoaded(val chatAIModels: List<ChatAIModel>) : ListModelViewEvent()

}

sealed class ListModelViewEffect{

}