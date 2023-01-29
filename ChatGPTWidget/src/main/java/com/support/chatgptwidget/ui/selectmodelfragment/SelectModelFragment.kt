package com.support.chatgptwidget.ui.selectmodelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.support.chatgptwidget.R

class SelectModelFragment : Fragment() {

    companion object {
        fun newInstance() = SelectModelFragment()
    }

    private lateinit var viewModel: SelectModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_model, container, false)
    }

}