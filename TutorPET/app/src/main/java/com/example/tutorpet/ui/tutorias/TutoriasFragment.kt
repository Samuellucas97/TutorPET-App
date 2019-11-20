package com.example.tutorpet.ui.tutorias

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.TutoriaAgendadaAdapter
import com.example.tutorpet.model.TutoriaAgendada
import com.example.tutorpet.view.AddDisciplinaTutoria
import com.example.tutorpet.view.Tutoria_location
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_tutorias.*
import org.json.JSONObject

class TutoriasFragment : Fragment() {

    private var listMonitoriasAgendadas = mutableListOf<TutoriaAgendada>()

    private var monitoriaAgendadaAdapter = TutoriaAgendadaAdapter( listMonitoriasAgendadas,
        this::onMonitoriaAgendadaItemLongClick, this::onMonitoriaAgendadaItemClick)

    lateinit var tutoriaAgendada: TutoriaAgendada

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tutorias, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lerDados()

        fabAdd.setOnClickListener {
            startActivity(Intent(activity, AddDisciplinaTutoria::class.java ))
        }
    }

    private fun onMonitoriaAgendadaItemLongClick(tutoriaAgendada: TutoriaAgendada): Boolean {
        var alertDialog  = AlertDialog.Builder(activity)

        alertDialog.setTitle(" Excluir Tutoria ")
        alertDialog.setMessage("Pretende excluir a tutoria de ${tutoriaAgendada.materiaMonitoria} com o tutor ${tutoriaAgendada.nome}.")

        alertDialog.setPositiveButton("Sim", {_,_ ->


        })

        alertDialog.setNegativeButton("Não", {_,_->})
        alertDialog.show()
        return true
    }

    private fun onMonitoriaAgendadaItemClick(tutoriaAgendada: TutoriaAgendada) {
        " Direciona o usuráio para a localização da tutoria agendada "

        var intent = Intent(activity, Tutoria_location::class.java)
        startActivity( intent )
    }

    fun lerDados(){

        listMonitoriasAgendadas.clear()

        db.collection("monitoriasAgendadas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var monitoriaAgendada = TutoriaAgendada(
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
//        val tutoriaAgendada = TutoriaAgendada()
//
//        val u = mapOf(
//            "diaDaSemana" to tutoriaAgendada.diaDaSemana,
//            "hora" to tutoriaAgendada.hora,
//            "local" to tutoriaAgendada.local,
//            "nome" to tutoriaAgendada.nome,
//            "materiaMonitoria" to tutoriaAgendada.materiaMonitoria
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