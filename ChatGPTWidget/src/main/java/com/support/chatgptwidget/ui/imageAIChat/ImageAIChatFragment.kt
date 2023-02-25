package com.support.chatgptwidget.ui.imageAIChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentImageAiChatBinding
import com.support.chatgptwidget.ui.imageAIChat.recyclerview.ImageAIChatRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageAIChatFragment : GPTFragment<FragmentImageAiChatBinding,ImageAIChatViewModel>(){
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageAiChatBinding {
        return FragmentImageAiChatBinding.inflate(inflater,container,false)
    }

    override fun initViewModel(): ImageAIChatViewModel {
        return ViewModelProvider(this)[ImageAIChatViewModel::class.java]
    }

    override fun initViews() {
        binding.rvChats.adapter = ImageAIChatRecyclerAdapter()
    }

    override fun initListeners() {
        binding.btnSend.setOnClickListener {
            sendMessage(binding.etConversation.text.toString())
        }
    }

    override fun effectObservers() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.viewEffect.collect {
                when(it){
                    ImageAIChatViewEffect.ImageAIChatInitialized -> {

                    }
                    is ImageAIChatViewEffect.LoadImage -> {

                    }
                }
            }
        }
    }

    private fun sendMessage(message: String){

    }

}