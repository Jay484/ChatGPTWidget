package com.support.chatgptwidget.ui.listmodels

import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIChatRepositoryImpl
import com.support.chatgptwidget.data.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListModelsViewModel : ViewModel() {
    var listModelsViewState = ListModelsViewState()
    var listModelViewEffect = MutableSharedFlow<ListModelViewEffect>()
    var apiToken: String? = null
    fun getChatAIModelList(){
        CoroutineScope(Dispatchers.IO).launch {
            AIChatRepositoryImpl(
                APIService.getChatGPTApiService(apiToken)
            ).getAIModels().collectLatest {
                processEvent(it)
            }
        }
    }



    private suspend fun processEvent(event: ListModelViewEvent){
        when(event){
            ListModelViewEvent.Error -> println()
            ListModelViewEvent.LoadingModels -> {
                listModelViewEffect.emit(
                    ListModelViewEffect.ShowListLoading
                )
            }
            is ListModelViewEvent.ModelsLoaded -> {
                listModelsViewState.chatAIModels = event.chatAIModels
                listModelViewEffect.emit(
                    ListModelViewEffect.LoadModels(event.chatAIModels)
                )
            }
        }
    }


}