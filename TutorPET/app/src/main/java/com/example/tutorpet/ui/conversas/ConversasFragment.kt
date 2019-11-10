package com.example.tutorpet.ui.conversas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.ConversaAdapter
import com.example.tutorpet.model.Conversa
import kotlinx.android.synthetic.main.fragment_conversas.*

class ConversasFragment : Fragment() {
    private var listConversas = mutableListOf<Conversa>(
        Conversa("João", "Grafos"),
        Conversa("Marcelo", "Linguagem de Programação I"),
        Conversa("Lavínia", "Estrutura de Dados Básico I"),
        Conversa("Amanda", "Compiladores")
    )

    private var conversaAdapter = ConversaAdapter( listConversas,
        this::onConversaItemLongClick, this::onConversaItemClick)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_conversas, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvConversas.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = conversaAdapter
        }
    }


    private fun onConversaItemLongClick(conversa: Conversa): Boolean {


        return true
    }

    private fun onConversaItemClick(conversa: Conversa) {

    }


}