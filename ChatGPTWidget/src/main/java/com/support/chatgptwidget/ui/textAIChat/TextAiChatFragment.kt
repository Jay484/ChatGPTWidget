package com.support.chatgptwidget.ui.textAIChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentChatBinding

class TextAiChatFragment : GPTFragment<FragmentChatBinding, TextAiChatViewModel>() {
    companion object {
        fun newInstance() = TextAiChatFragment()
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater, container, false)
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

}