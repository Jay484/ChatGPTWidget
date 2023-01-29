package com.support.chatgptwidget.ui.selectmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.support.chatgptwidget.models.ChatAIModel
import com.support.chatgptwidget.network.chatGPTApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectModelViewModel : ViewModel() {
    val modelList : MutableLiveData<List<ChatAIModel>?> = MutableLiveData(null)
    fun getChatAIModelList(){
        CoroutineScope(Dispatchers.IO).launch {
            modelList.postValue(chatGPTApiService.getModels().data)
        }
    }


}