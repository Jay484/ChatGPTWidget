package com.support.chatgptwidget.ui.listmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.support.chatgptwidget.R
import com.support.chatgptwidget.network.models.responsemodels.ChatAIModel
import com.support.chatgptwidget.ui.listmodels.listeners.ModelItemListeners

class AIModelsRecyclerViewAdapter : RecyclerView.Adapter<AIModelsRecyclerViewAdapter.AIModelViewHolder>(){
    var models : List<ChatAIModel> = listOf()
    var modelItemListeners : ModelItemListeners? = null

    inner class AIModelViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item : ChatAIModel){
            itemView.findViewById<TextView>(R.id.tv_model_id).apply {
                text = item.id
                setOnClickListener {
                    modelItemListeners?.onClickModel(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AIModelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_models_layout, parent, false)
        return AIModelViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: AIModelViewHolder, position: Int){
        holder.bind(models[position])
    }
}