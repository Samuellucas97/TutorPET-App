package com.example.tutorpet.ui.conversas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tutorpet.R

class ConversasFragment : Fragment() {

    private lateinit var conversasViewModel: ConversasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        conversasViewModel =
            ViewModelProviders.of(this).get(ConversasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_conversas, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        conversasViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}