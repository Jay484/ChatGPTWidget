package com.support.chatgptwidget.ui.listmodels

import com.support.chatgptwidget.data.network.models.responsemodels.ChatAIModel

data class ListModelsViewState(
        var chatAIModels: List<ChatAIModel> = listOf()
)

sealed class ListModelViewEvent{

    object LoadingModels : ListModelViewEvent()
    data class ModelsLoaded(val chatAIModels: List<ChatAIModel>) : ListModelViewEvent()
    object Error : ListModelViewEvent()

}

sealed class ListModelViewEffect{
    object ShowListLoading: ListModelViewEffect()
    class LoadModels(val chatAIModels : List<ChatAIModel>):ListModelViewEffect()
}