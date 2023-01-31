package com.support.chatgptwidget.ui.listmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.data.AIModelRepositoryImpl
import com.support.chatgptwidget.models.ChatAIModel
import com.support.chatgptwidget.network.chatGPTApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListModelsViewModel : ViewModel() {
    val modelList : MutableLiveData<List<ChatAIModel>?> = MutableLiveData(null)
    fun getChatAIModelList(){
        CoroutineScope(Dispatchers.IO).launch {
            AIModelRepositoryImpl(chatGPTApiService).getAIModels().collectLatest {
                modelList.postValue(it)
            }
        }
    }


}