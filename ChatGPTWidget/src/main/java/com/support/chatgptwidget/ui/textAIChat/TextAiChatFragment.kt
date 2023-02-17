package com.support.chatgptwidget.ui.textAIChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.support.chatgptwidget.AIChatActivity
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentTextAiChatBinding
import com.support.chatgptwidget.ui.textAIChat.recyclerview.TextAIChatRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextAiChatFragment : GPTFragment<FragmentTextAiChatBinding, TextAiChatViewModel>() {

    private lateinit var textAIChatRecyclerAdapter: TextAIChatRecyclerAdapter
    private val args: TextAiChatFragmentArgs by navArgs()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTextAiChatBinding {
        return FragmentTextAiChatBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): TextAiChatViewModel {
        return ViewModelProvider(this)[TextAiChatViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val action = TextAiChatFragmentDirections
                .actionTextAiChatFragmentToCategoryFragment()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model = args.model
        viewModel.apiToken = (requireActivity() as AIChatActivity).apiKey
    }

    override fun initListeners(){
        binding.btnSend.setOnClickListener {
            sendMessage(binding.etConversation.text.toString())
        }
        binding.etConversation.doOnTextChanged { text, _, _, _ ->
                binding.btnSend.isEnabled = !text.isNullOrEmpty()
        }
    }

    override fun effectObservers() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.viewEffect.collect {
                when(it){
                    is TextAiChatViewEffect.LoadMessage -> {
                        recyclerViewNewItemAdded()
                    }
                    TextAiChatViewEffect.ViewModelInitialized -> {

                    }
                }
            }
        }
    }

    override fun initViews() {
        textAIChatRecyclerAdapter = TextAIChatRecyclerAdapter()
        binding.rvChats.adapter = textAIChatRecyclerAdapter
        textAIChatRecyclerAdapter.data = viewModel.data
        binding.btnSend.isEnabled = false
    }

    private fun sendMessage(text: String){
        viewModel.completeText(text)
        binding.etConversation.text = null
        recyclerViewNewItemAdded()
    }

    private fun recyclerViewNewItemAdded(){
        val position = viewModel.data.size-1
        textAIChatRecyclerAdapter.notifyItemInserted(position)
        binding.rvChats.smoothScrollToPosition(position)
    }

}