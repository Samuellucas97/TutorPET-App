package com.example.tutorpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorpet.R
import com.example.tutorpet.model.TutoriaAgendada
import kotlinx.android.synthetic.main.item_tutoria_agendada.view.*

class TutoriaAgendadaAdapter(

    private val listTutoriasAgendadas: List<TutoriaAgendada>,
    private val callback: (TutoriaAgendada) -> Boolean,   // Usado no click longo
    private val callbackTwo: (TutoriaAgendada) -> Unit    // Usado no click curto

): RecyclerView.Adapter<TutoriaAgendadaAdapter.VH> () {

    class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtDiaDaSemana: TextView = itemView.txtDiaDaSemana
        val txtHora: TextView = itemView.txtHora
        val txtLocal: TextView = itemView.txtLocal
        val txtNome: TextView = itemView.txtNome
        val txtMateriaMonitoria: TextView = itemView.txtMateriaMonitoria
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tutoria_agendada, parent, false)

        val viewHolder = VH(v)
        viewHolder.itemView.setOnLongClickListener{
            val tutoriaAgendada = listTutoriasAgendadas[viewHolder.adapterPosition]
            callback(tutoriaAgendada)
        }

        viewHolder.itemView.setOnClickListener {
            val tutoriaAgendada = listTutoriasAgendadas[viewHolder.adapterPosition]
            callbackTwo(tutoriaAgendada)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = listTutoriasAgendadas.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (id, diaDaSemana, hora, local, nome , materiaMonitoria) = listTutoriasAgendadas[position]

        holder. apply {
            txtDiaDaSemana.text = diaDaSemana
            txtHora.text = hora
            txtLocal.text = local
            txtNome.text = nome
            txtMateriaMonitoria.text = materiaMonitoria

        }
    }
}
