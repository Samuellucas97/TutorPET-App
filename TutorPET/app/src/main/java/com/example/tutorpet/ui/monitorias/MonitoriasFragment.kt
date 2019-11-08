package com.example.tutorpet.ui.monitorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.MonitoriaAgendadaAdapter
import com.example.tutorpet.model.MonitoriaAgendada
import kotlinx.android.synthetic.main.fragment_monitorias.*

class MonitoriasFragment : Fragment() {

    private lateinit var monitoriasViewModel: MonitoriasViewModel

    private var listMonitoriasAgendadas = mutableListOf<MonitoriaAgendada>()

    private var monitoriaAgendadaAdapter = MonitoriaAgendadaAdapter( listMonitoriasAgendadas,
        this::onMonitoriaAgendadaItemLongClick, this::onMonitoriaAgendadaItemClick)

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

        rvMonitorias.adapter = monitoriaAgendadaAdapter
        rvMonitorias.layoutManager = LinearLayoutManager(context)

        return root
    }

    private fun onMonitoriaAgendadaItemLongClick(monitoriaAgendada: MonitoriaAgendada): Boolean {
        // TODO

        return true
    }

    private fun onMonitoriaAgendadaItemClick(monitoriaAgendada: MonitoriaAgendada) {
        // TODO
    }


}