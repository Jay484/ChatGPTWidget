package com.support.chatgptwidget.ui.listmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.support.chatgptwidget.AIChatActivity
import com.support.chatgptwidget.databinding.FragmentListModelsBinding
import com.support.chatgptwidget.data.network.models.responsemodels.ChatAIModel
import com.support.chatgptwidget.ui.listmodels.listeners.ModelItemListeners
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListModelsFragment : Fragment() {
    private lateinit var modelRecyclerView: RecyclerView
    private lateinit var modelsRecyclerViewAdapter: AIModelsRecyclerViewAdapter
    private lateinit var binding: FragmentListModelsBinding

    private val viewModel: ListModelsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.apiToken = (requireActivity() as AIChatActivity).apiKey
        binding = FragmentListModelsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeFlows()
        viewModel.getChatAIModelList()
    }

    private fun initViews(){
        modelRecyclerView = binding.rvAiModels
        modelsRecyclerViewAdapter = AIModelsRecyclerViewAdapter().apply {
            modelItemListeners = object : ModelItemListeners{
                override fun onClickModel(id: String) {
                }
            }
        }
        modelRecyclerView.adapter = modelsRecyclerViewAdapter
    }
    private fun observeFlows(){
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.listModelViewEffect.collect {
                when (it) {
                    is ListModelViewEffect.LoadModels -> {
                        withContext(Dispatchers.Main) {
                            hideLoading()
                            showModelsList(it.chatAIModels)
                        }
                    }
                    ListModelViewEffect.ShowListLoading -> showLoading()
                }
            }
        }
    }

    private fun showLoading(){
        binding.pbScreen.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.pbScreen.visibility = View.GONE
    }

    private fun showModelsList(modelList: List<ChatAIModel>){
        modelsRecyclerViewAdapter.models = modelList
        modelsRecyclerViewAdapter.notifyItemRangeInserted(0,modelList.size)
    }

}