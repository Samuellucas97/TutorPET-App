package com.example.tutorpet.ui.monitorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.MonitoriaAgendadaAdapter
import com.example.tutorpet.model.MonitoriaAgendada
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_monitorias.*
import org.json.JSONObject

class MonitoriasFragment : Fragment() {

    private var listMonitoriasAgendadas = mutableListOf<MonitoriaAgendada>()

    private var monitoriaAgendadaAdapter = MonitoriaAgendadaAdapter( listMonitoriasAgendadas,
        this::onMonitoriaAgendadaItemLongClick, this::onMonitoriaAgendadaItemClick)

    lateinit var monitoriaAgendada: MonitoriaAgendada

    val db = FirebaseFirestore.getInstance()

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
        lerDados()
    }

    private fun onMonitoriaAgendadaItemLongClick(monitoriaAgendada: MonitoriaAgendada): Boolean {
        return true
    }

    private fun onMonitoriaAgendadaItemClick(monitoriaAgendada: MonitoriaAgendada) {
    }

    fun lerDados(){

        listMonitoriasAgendadas.clear()

        db.collection("monitoriasAgendadas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var monitoriaAgendada = MonitoriaAgendada(
                        id = doc.id,
                        diaDaSemana = jo.getString("diaDaSemana"),
                        hora = jo.getString("hora"),
                        local = jo.getString("local"),
                        nome = jo.getString("nome"),
                        materiaMonitoria = jo.getString("materiaMonitoria")
                    )
                    listMonitoriasAgendadas.add(monitoriaAgendada)
                }

                rvMonitorias.adapter = monitoriaAgendadaAdapter
                rvMonitorias.layoutManager = LinearLayoutManager(context)

            }
            .addOnFailureListener{e->
                Toast.makeText(activity, "Erro: $e",Toast.LENGTH_LONG).show()
            }
    }

//    fun addDados() {
//
//        val monitoriaAgendada = MonitoriaAgendada()
//
//        val u = mapOf(
//            "diaDaSemana" to monitoriaAgendada.diaDaSemana,
//            "hora" to monitoriaAgendada.hora,
//            "local" to monitoriaAgendada.local,
//            "nome" to monitoriaAgendada.nome,
//            "materiaMonitoria" to monitoriaAgendada.materiaMonitoria
//        )
//
//        db.collection("monitoriasAgendadas")
//            .add(u)
//            .addOnSuccessListener {
//                Toast.makeText(
//                    activity,
//                    "Adicionado com sucesso!",
//                    Toast.LENGTH_LONG).show()
//
//                    lerDados()
//
//            }
//            .addOnFailureListener {e ->
//                Toast.makeText(
//                    activity,
//                    "Erro ao Adicionado: $e",
//                    Toast.LENGTH_LONG).show()
//            }
//    }

}