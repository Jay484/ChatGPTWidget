package com.support.chatgptwidget.ui.textAIChat.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.support.chatgptwidget.R
import com.support.chatgptwidget.models.AIChatMessage

class TextAIChatRecyclerAdapter : RecyclerView.Adapter<TextAICHatViewHolder>() {
    var data = listOf<AIChatMessage>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAICHatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when(viewType){
            0 ->{
                return TextAICHatViewHolder.Sent(
                        layoutInflater.inflate(
                            R.layout.item_chat_sent,
                            parent,
                            false
                        )
                    )
            }
            else -> {
                return TextAICHatViewHolder.Received(
                    layoutInflater.inflate(
                        R.layout.item_chat_received,
                        parent,
                        false
                    )
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
        return if(data[position].sender == "me") 0 else 1
    }
}

sealed class TextAICHatViewHolder(view: View):ViewHolder(view){
    class Sent(view: View): TextAICHatViewHolder(view){
        fun bind(aiChatMessage: AIChatMessage){
            itemView.findViewById<TextView>(R.id.tv_sent_message).text = aiChatMessage.text
        }
    }

    class Received(view: View): TextAICHatViewHolder(view){
        fun bind(aiChatMessage: AIChatMessage){
            itemView.findViewById<TextView>(R.id.tv_received_message).text = aiChatMessage.text
        }
    }
}