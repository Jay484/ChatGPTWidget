package com.support.chatgptwidget.ui.selectmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.support.chatgptwidget.R
import com.support.chatgptwidget.models.ChatAIModel

class AIModelsRecyclerViewAdapter : RecyclerView.Adapter<AIModelsRecyclerViewAdapter.AIModelViewHolder>(){
    var models : List<ChatAIModel> = listOf()

    inner class AIModelViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item : ChatAIModel){
            itemView.findViewById<TextView>(R.id.tv_model_id).text = item.id
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