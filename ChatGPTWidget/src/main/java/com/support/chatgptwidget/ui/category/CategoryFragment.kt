package com.support.chatgptwidget.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        }
        binding.btnCodeCompletion.setOnClickListener {

        }
        binding.btnGenerateImages.setOnClickListener {

        }
    }


}