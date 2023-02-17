package com.support.chatgptwidget.ui.imageAIChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.support.chatgptwidget.baseclasses.GPTFragment
import com.support.chatgptwidget.databinding.FragmentImageAiChatBinding

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
    }

    override fun initListeners() {

    }

    override fun effectObservers() {

    }

}