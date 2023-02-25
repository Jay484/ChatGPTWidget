package com.support.chatgptwidget.ui.imageAIChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.support.chatgptwidget.AIChatActivity
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentImageAiChatBinding
import com.support.chatgptwidget.ui.imageAIChat.recyclerview.ImageAIChatRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageAIChatFragment : GPTFragment<FragmentImageAiChatBinding,ImageAIChatViewModel>(){

    private lateinit var imageAIChatRecyclerAdapter: ImageAIChatRecyclerAdapter
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageAiChatBinding {
        return FragmentImageAiChatBinding.inflate(inflater,container,false)
    }

    override fun initViewModel(): ImageAIChatViewModel {
        return ViewModelProvider(this)[ImageAIChatViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apiToken = (requireActivity() as AIChatActivity).apiKey
    }

    override fun initViews() {
        imageAIChatRecyclerAdapter = ImageAIChatRecyclerAdapter()
        imageAIChatRecyclerAdapter.data = viewModel.data
        binding.rvChats.adapter = imageAIChatRecyclerAdapter
        binding.btnSend.isEnabled = false
    }

    override fun initListeners() {
        binding.btnSend.setOnClickListener {
            sendMessage(binding.etConversation.text.toString())
        }

        binding.etConversation.doOnTextChanged { text, _, _, _ ->
            binding.btnSend.isEnabled = !text.isNullOrEmpty()
        }
    }

    override fun effectObservers() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.viewEffect.collect {
                when(it){
                    ImageAIChatViewEffect.ImageAIChatInitialized -> {

                    }
                    is ImageAIChatViewEffect.LoadImage -> {
                        withContext(Dispatchers.Main){
                            recyclerViewNewItemAdded()
                        }
                    }
                }
            }
        }
    }

    private fun sendMessage(message: String){
        viewModel.getImages(message)
        binding.etConversation.text = null
        recyclerViewNewItemAdded()
    }

    private fun recyclerViewNewItemAdded(){
        val position = viewModel.data.size-1
        imageAIChatRecyclerAdapter.notifyItemInserted(position)
        binding.rvChats.smoothScrollToPosition(position)
    }

}