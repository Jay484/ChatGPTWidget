package com.support.chatgptwidget.ui.listmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.support.chatgptwidget.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListModelsFragment : Fragment() {
    private lateinit var modelRecyclerView: RecyclerView
    private lateinit var modelsRecyclerViewAdapter: AIModelsRecyclerViewAdapter
    companion object {
        fun newInstance() = ListModelsFragment()
    }

    private val viewModel: ListModelsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_models, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelRecyclerView = view.findViewById(R.id.rv_ai_models)
        modelsRecyclerViewAdapter = AIModelsRecyclerViewAdapter()
        modelRecyclerView.adapter = modelsRecyclerViewAdapter
        viewModel.getChatAIModelList()
        viewModel.modelList.observe(viewLifecycleOwner) {
            it?.let {models->
                modelsRecyclerViewAdapter.models = models
                CoroutineScope(Dispatchers.Main).launch {
                    modelsRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        }
    }

}