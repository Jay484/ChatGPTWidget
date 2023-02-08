package com.support.chatgptwidget.ui.textAIChat.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.support.chatgptwidget.R
import com.support.chatgptwidget.models.AIChatMessage

class TextAIChatRecyclerAdapter : RecyclerView.Adapter<TextAIChatSendViewHolder>() {
    var data = listOf<AIChatMessage>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAIChatSendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =
            if(viewType == 0)
                layoutInflater.inflate(R.layout.item_chat_sent, parent, false)
            else
                layoutInflater.inflate(R.layout.item_chat_received, parent, false)
        return TextAIChatSendViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextAIChatSendViewHolder, position: Int) {
        val aiChatMessage = data[position]
        holder.bind(aiChatMessage)
    }

    private fun bind(holder: TextAIChatSendViewHolder, aiChatMessage: AIChatMessage){

    }

    override fun getItemViewType(position: Int): Int {
        return if(data[position].sender == "me") 0 else 1
    }
}

class TextAIChatSendViewHolder(val view: View): ViewHolder(view){
}

fun ViewHolder.bind(aiChatMessage: AIChatMessage){
    itemView.findViewById<TextView>(R.id.tv_sent_message).text = aiChatMessage.text
}