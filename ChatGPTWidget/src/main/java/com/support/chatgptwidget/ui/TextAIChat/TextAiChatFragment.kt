package com.support.chatgptwidget.ui.TextAIChat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.support.chatgptwidget.R
import com.support.chatgptwidget.ui.listmodels.ListModelsFragmentDirections

class TextAiChatFragment : Fragment() {

    companion object {
        fun newInstance() = TextAiChatFragment()
    }

    private lateinit var viewModel: TextAiChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_ai_chat, container, false)
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