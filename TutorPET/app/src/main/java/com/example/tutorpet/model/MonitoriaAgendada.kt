package com.example.tutorpet.model

data class MonitoriaAgendada (
    var id: String,
    var diaDaSemana: String = "Desconhecido",
    var hora: String = "Desconhecido",
    var local: String = "Desconhecido",
    var nome: String = "Desconhecido",
    var materiaMonitoria: String = "Desconhecido"
)