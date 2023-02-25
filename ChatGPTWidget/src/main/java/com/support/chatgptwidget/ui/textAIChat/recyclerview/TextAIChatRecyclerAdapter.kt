package com.support.chatgptwidget.ui.textAIChat.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.support.chatgptwidget.databinding.ItemTextChatReceivedBinding
import com.support.chatgptwidget.databinding.ItemTextChatSentBinding
import com.support.chatgptwidget.models.AIChatTextMessage

class TextAIChatRecyclerAdapter : RecyclerView.Adapter<TextAIChatViewHolder>() {
    var data = arrayListOf<AIChatTextMessage>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAIChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            0 ->{
                TextAIChatViewHolder.Sent(
                    ItemTextChatSentBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> {
                TextAIChatViewHolder.Received(
                    ItemTextChatReceivedBinding.inflate(layoutInflater,parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextAIChatViewHolder, position: Int) {
        val aiChatMessage = data[position]
        when(holder){
            is TextAIChatViewHolder.Received -> {
                holder.bind(aiChatMessage)
            }
            is TextAIChatViewHolder.Sent -> {
                holder.bind(aiChatMessage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].sender.type
    }
}

sealed class TextAIChatViewHolder(view: View):ViewHolder(view){
    class Sent(private val binding: ItemTextChatSentBinding):TextAIChatViewHolder(binding.root){
        fun bind(aiChatTextMessage: AIChatTextMessage){
            binding.tvSentMessage.text = aiChatTextMessage.text
        }
    }

    class Received(private val binding: ItemTextChatReceivedBinding):TextAIChatViewHolder(binding.root){
        fun bind(aiChatTextMessage: AIChatTextMessage){
            binding.tvReceivedMessage.text = aiChatTextMessage.text
        }
    }
}