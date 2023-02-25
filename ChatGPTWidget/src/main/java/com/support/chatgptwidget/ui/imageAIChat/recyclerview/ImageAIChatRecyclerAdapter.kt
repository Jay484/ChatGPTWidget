package com.support.chatgptwidget.ui.imageAIChat.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.support.chatgptwidget.databinding.ItemImageChatReceivedBinding
import com.support.chatgptwidget.databinding.ItemImageChatSentBinding
import com.support.chatgptwidget.models.AIChatImageMessage
import com.support.chatgptwidget.models.Sender

class ImageAIChatRecyclerAdapter : RecyclerView.Adapter<ImageAIChatViewHolder>() {
    var data = arrayListOf<AIChatImageMessage>(
        AIChatImageMessage(
            Sender.Me,
            "https://oaidalleapiprodscus.blob.core.windows.net/private/org-tuC4PvR6S2U2d5c8CdSE80Ix/user-hNfuOlNnoNbf25LuLNxYIHWw/img-0JxNcKr88yaVmW6hJOp6sEyr.png?st=2023-02-25T00%3A34%3A12Z&se=2023-02-25T02%3A34%3A12Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-02-24T20%3A38%3A45Z&ske=2023-02-25T20%3A38%3A45Z&sks=b&skv=2021-08-06&sig=5QcaUPIOWe39VjBdXezUB2ncVRYbwEutzJVZgAazK1Q%3D",
            "baby otter"
        ),
        AIChatImageMessage(
            Sender.Bot,
            "https://oaidalleapiprodscus.blob.core.windows.net/private/org-tuC4PvR6S2U2d5c8CdSE80Ix/user-hNfuOlNnoNbf25LuLNxYIHWw/img-3T6EmYxdnfd3fCZRIzGvkxlT.png?st=2023-02-25T00%3A34%3A12Z&se=2023-02-25T02%3A34%3A12Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-02-24T20%3A38%3A45Z&ske=2023-02-25T20%3A38%3A45Z&sks=b&skv=2021-08-06&sig=vOCBFgYS19Qd%2BLp8p060sRqY5by0OafmW97oKNRQnk0%3D",
            null
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAIChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                ImageAIChatViewHolder.Sent(
                    ItemImageChatSentBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> {
                ImageAIChatViewHolder.Received(
                    ItemImageChatReceivedBinding.inflate(layoutInflater, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ImageAIChatViewHolder, position: Int) {
        val aiImageMessage = data[position]
        when (holder) {
            is ImageAIChatViewHolder.Sent -> {
                holder.bind(aiImageMessage)
            }
            is ImageAIChatViewHolder.Received -> {
                holder.bind(aiImageMessage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = data[position].sender.type
}

sealed class ImageAIChatViewHolder(view: View) : ViewHolder(view) {

    class Sent(
        private val binding: ItemImageChatSentBinding
    ) : ImageAIChatViewHolder(binding.root) {
        fun bind(aiChatTextMessage: AIChatImageMessage) {
            if(aiChatTextMessage.imgUrl != null)
                binding.ivSent.visibility = View.VISIBLE
            else
                binding.ivSent.visibility = View.GONE
            Glide.with(binding.root.context)
                .load(aiChatTextMessage.imgUrl)
                .into(binding.ivSent)

            binding.tvSentMessage.text = aiChatTextMessage.description
        }
    }

    class Received(
        private val binding: ItemImageChatReceivedBinding
    ) : ImageAIChatViewHolder(binding.root) {

        fun bind(aiChatImageMessage: AIChatImageMessage) {
            Glide.with(binding.root.context)
                .load(aiChatImageMessage.imgUrl)
                .into(binding.ivReceived)
        }
    }
}