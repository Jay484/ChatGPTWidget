package com.support.chatgptwidget.ui.textAIChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.doOnTextChanged
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
        return TextAiChatViewModel()
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
            if(text != null){
                binding.btnSend.visibility = if (text.isEmpty())
                    View.GONE
                else
                    View.VISIBLE
            }
        }
    }

    override fun effectObservers() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.viewEffect.collect {
                when(it){
                    is TextAiChatViewEffect.LoadMessage -> {
                        textAIChatRecyclerAdapter
                            .notifyItemInserted(viewModel.data.size-1)
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
    }

    private fun sendMessage(text: String){
        viewModel.completeText(text)
        binding.etConversation.text = null
        textAIChatRecyclerAdapter.notifyItemInserted(viewModel.data.size-1)
    }

}