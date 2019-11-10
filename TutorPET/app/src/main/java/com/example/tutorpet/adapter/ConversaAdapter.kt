package com.example.tutorpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorpet.R
import com.example.tutorpet.model.Conversa
import kotlinx.android.synthetic.main.item_conversa.view.*

class ConversaAdapter (private val listConversas: List<Conversa>,
                       private val callback: (Conversa) -> Boolean,   // Usado no click longo
                       private val callbackTwo: (Conversa) -> Unit    // Usado no click curto

): RecyclerView.Adapter<ConversaAdapter.VH> () {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.txtNome
        val txtMateriaMonitoria: TextView = itemView.txtMateriaMonitoria
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_conversa, parent, false)

        val viewHolder = VH(v)
        viewHolder.itemView.setOnLongClickListener {
            val conversa = listConversas[viewHolder.adapterPosition]
            callback(conversa)
        }

        viewHolder.itemView.setOnClickListener {
            val conversa = listConversas[viewHolder.adapterPosition]
            callbackTwo(conversa)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = listConversas.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (nome, materiaMonitoria) = listConversas[position]

        holder.apply {
            txtNome.text = nome
            txtMateriaMonitoria.text = materiaMonitoria
        }
    }
}