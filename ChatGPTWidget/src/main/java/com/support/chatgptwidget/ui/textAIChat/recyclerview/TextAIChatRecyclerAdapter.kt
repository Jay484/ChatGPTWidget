package com.support.chatgptwidget.ui.textAIChat.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.support.chatgptwidget.databinding.ItemChatReceivedBinding
import com.support.chatgptwidget.databinding.ItemChatSentBinding
import com.support.chatgptwidget.models.AIChatMessage

class TextAIChatRecyclerAdapter : RecyclerView.Adapter<TextAICHatViewHolder>() {
    var data = arrayListOf<AIChatMessage>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAICHatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            0 ->{
                TextAICHatViewHolder.Sent(
                    ItemChatSentBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> {
                TextAICHatViewHolder.Received(
                    ItemChatReceivedBinding.inflate(layoutInflater,parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextAICHatViewHolder, position: Int) {
        val aiChatMessage = data[position]
        when(holder){
            is TextAICHatViewHolder.Received -> {
                holder.bind(aiChatMessage)
            }
            is TextAICHatViewHolder.Sent -> {
                holder.bind(aiChatMessage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].sender.type
    }
}

sealed class TextAICHatViewHolder(view: View):ViewHolder(view){
    class Sent(private val binding: ItemChatSentBinding): TextAICHatViewHolder(binding.root){
        fun bind(aiChatMessage: AIChatMessage){
            binding.tvSentMessage.text = aiChatMessage.text
        }
    }

    class Received(private val binding: ItemChatReceivedBinding): TextAICHatViewHolder(binding.root){
        fun bind(aiChatMessage: AIChatMessage){
            binding.tvReceivedMessage.text = aiChatMessage.text
        }
    }
}