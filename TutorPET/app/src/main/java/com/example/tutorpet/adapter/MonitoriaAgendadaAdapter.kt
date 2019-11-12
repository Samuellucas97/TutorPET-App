package com.example.tutorpet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorpet.R
import com.example.tutorpet.model.MonitoriaAgendada
import kotlinx.android.synthetic.main.item_monitoria_agendada.view.*

class MonitoriaAgendadaAdapter(

    private val listMonitoriasAgendadas: List<MonitoriaAgendada>,
    private val callback: (MonitoriaAgendada) -> Boolean,   // Usado no click longo
    private val callbackTwo: (MonitoriaAgendada) -> Unit    // Usado no click curto

): RecyclerView.Adapter<MonitoriaAgendadaAdapter.VH> () {

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
            .inflate(R.layout.item_monitoria_agendada, parent, false)

        val viewHolder = VH(v)
        viewHolder.itemView.setOnLongClickListener{
            val monitoriaAgendada = listMonitoriasAgendadas[viewHolder.adapterPosition]
            callback(monitoriaAgendada)
        }

        viewHolder.itemView.setOnClickListener {
            val monitoriaAgendada = listMonitoriasAgendadas[viewHolder.adapterPosition]
            callbackTwo(monitoriaAgendada)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = listMonitoriasAgendadas.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (id, diaDaSemana, hora, local, nome , materiaMonitoria) = listMonitoriasAgendadas[position]

        holder. apply {
            txtDiaDaSemana.text = diaDaSemana
            txtHora.text = hora
            txtLocal.text = local
            txtNome.text = nome
            txtMateriaMonitoria.text = materiaMonitoria

        }
    }
}
