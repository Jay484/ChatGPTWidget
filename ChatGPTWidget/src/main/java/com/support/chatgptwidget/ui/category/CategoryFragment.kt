package com.support.chatgptwidget.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.support.chatgptwidget.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        initOnclickListeners()
        return binding.root
    }
    private fun initOnclickListeners(){
        binding.btnTextCompletion.setOnClickListener {
            val direction = CategoryFragmentDirections
                .actionCategoryFragmentToTextAiChatFragment(text_davinci_003_model)
            findNavController().navigate(direction)
        }
        binding.btnCodeCompletion.setOnClickListener {

        }
        binding.btnGenerateImages.setOnClickListener {
            val direction = CategoryFragmentDirections.actionCategoryFragmentToImageAIChatFragment()
            findNavController().navigate(direction)
        }
    }

    companion object{
        const val text_davinci_003_model = "text-davinci-003"
    }

}