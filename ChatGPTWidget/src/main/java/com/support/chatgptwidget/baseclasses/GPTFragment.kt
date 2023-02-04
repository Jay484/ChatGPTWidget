package com.support.chatgptwidget.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class GPTFragment<T : ViewBinding, V: ViewModel> : Fragment() {
    lateinit var binding : T
    lateinit var viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(inflater, container)
        viewModel = initViewModel()
        return  binding.root
    }
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?):T
    abstract fun initViewModel():V
}