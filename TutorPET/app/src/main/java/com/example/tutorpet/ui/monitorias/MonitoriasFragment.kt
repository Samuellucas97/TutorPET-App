package com.example.tutorpet.ui.monitorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tutorpet.R

class MonitoriasFragment : Fragment() {

    private lateinit var monitoriasViewModel: MonitoriasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monitoriasViewModel =
            ViewModelProviders.of(this).get(MonitoriasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monitorias, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        monitoriasViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}