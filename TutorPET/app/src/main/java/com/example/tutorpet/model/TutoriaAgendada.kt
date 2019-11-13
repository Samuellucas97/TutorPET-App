package com.example.tutorpet.model

data class TutoriaAgendada (
    var id: String="",
    var diaDaSemana: String = "Segunda",
    var hora: String = "15pm",
    var local: String = "IMD",
    var nome: String = "Paulo",
    var materiaMonitoria: String = "Projeto de Sistemas Operacionais"
)