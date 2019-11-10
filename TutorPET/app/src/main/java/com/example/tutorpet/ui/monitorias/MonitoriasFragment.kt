package com.example.tutorpet.ui.monitorias

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.MonitoriaAgendadaAdapter
import com.example.tutorpet.model.MonitoriaAgendada
import kotlinx.android.synthetic.main.fragment_monitorias.*

class MonitoriasFragment : Fragment() {

    private var listMonitoriasAgendadas = mutableListOf<MonitoriaAgendada>(
        MonitoriaAgendada("XX", "Segunda", "14pm", "IMD", "João", "Grafos"),
        MonitoriaAgendada("AA", "Quarta", "17pm", "DIMAp", "Marcelo", "Linguagem de Programação I"),
        MonitoriaAgendada("BB", "Quinta", "10am", "DIMAp", "Lavínia", "Estrutura de Dados Básico I"),
        MonitoriaAgendada("SS", "Quinta", "13pm", "IMD", "Amanda", "Compiladores")
    )

    private var monitoriaAgendadaAdapter = MonitoriaAgendadaAdapter( listMonitoriasAgendadas,
        this::onMonitoriaAgendadaItemLongClick, this::onMonitoriaAgendadaItemClick)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_monitorias, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvMonitorias.adapter = monitoriaAgendadaAdapter
        rvMonitorias.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMonitorias.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = monitoriaAgendadaAdapter
        }
    }


    private fun onMonitoriaAgendadaItemLongClick(monitoriaAgendada: MonitoriaAgendada): Boolean {


        return true
    }

    private fun onMonitoriaAgendadaItemClick(monitoriaAgendada: MonitoriaAgendada) {

    }


}