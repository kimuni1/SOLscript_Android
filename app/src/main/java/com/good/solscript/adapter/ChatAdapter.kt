package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.ChatData
import com.good.solscript.databinding.RecyclerChatMyItemBinding
import com.good.solscript.databinding.RecyclerChatYourItemBinding


class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ChatMyViewHolder(val binding: RecyclerChatMyItemBinding) :
        RecyclerView.ViewHolder(binding.rlChatmyitemDataset)

    class ChatYourViewHolder(val binding: RecyclerChatYourItemBinding) :
        RecyclerView.ViewHolder(binding.rlChatyouritemDataset)

    var data = arrayListOf<ChatData>()

    override fun getItemViewType(position: Int): Int {
        val chatMessage = data[position]

        return if (chatMessage.id == "me") {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var viewHolder: RecyclerView.ViewHolder

        when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_chat_my_item, parent, false)

                viewHolder = ChatMyViewHolder(RecyclerChatMyItemBinding.bind(view))
                return viewHolder

            }
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_chat_your_item, parent, false)

                viewHolder = ChatYourViewHolder(RecyclerChatYourItemBinding.bind(view))
                return viewHolder
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val chatMessage = data[position]

        if (chatMessage.id == "me") {
            val holderMyViewHolder: ChatMyViewHolder = holder as ChatMyViewHolder
            holderMyViewHolder.binding.chatData = data[position]

        } else {
            val holderYourViewHolder: ChatYourViewHolder = holder as ChatYourViewHolder
            holderYourViewHolder.binding.chatData = data[position]
        }
    }

    fun addItem(item: ChatData) {
        data.add(item)
    }

}