package com.support.chatgptwidget.ui.textAIChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentTextAiChatBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextAiChatFragment : GPTFragment<FragmentTextAiChatBinding, TextAiChatViewModel>() {
    companion object {
        fun newInstance() = TextAiChatFragment()
    }

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
                .actionTextAiChatFragmentToListModelsFragment()
            findNavController().navigate(action)
        }
    }

    override fun initListeners(){
        binding.btnSend.setOnClickListener {
            viewModel.completeText(binding.etConversation.text.toString())
        }
    }

    override fun effectObservers() {
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.viewEffect.collect {
                when(it){
                    is TextAiChatViewEffect.LoadText -> {
                        binding.etConversation.setText(it.text)
                    }
                    TextAiChatViewEffect.ViewModelInitialized -> {

                    }
                }
            }
        }
    }

}