package com.example.tutorpet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorpet.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat_to.view.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(ChatItemFromAdapter("Oi"))
        adapter.add(ChatItemFromAdapter("Oi"))
        adapter.add(ChatItemTo("Como vai?"))
        adapter.add(ChatItemTo("Bem."))

        rvChat.adapter = adapter

    }

    class ChatItemFromAdapter(val text: String): Item<ViewHolder>() {

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView.text = text
        }
        override fun getLayout(): Int = R.layout.item_chat_from

    }


    class ChatItemTo(val text: String): Item<ViewHolder>() {

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView.text = text
        }
        override fun getLayout(): Int = R.layout.item_chat_to

    }
}


