package com.example.tutorpet.ui.conversas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.adapter.ConversaAdapter
import com.example.tutorpet.model.Conversa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_conversas.*
import org.json.JSONObject

class ConversasFragment : Fragment() {
    private var listConversas = mutableListOf<Conversa>()

    val db = FirebaseFirestore.getInstance()

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lerDados()
    }


    private fun onConversaItemLongClick(conversa: Conversa): Boolean {
        return true
    }

    private fun onConversaItemClick(conversa: Conversa) {

    }


    fun lerDados(){

        listConversas.clear()

        db.collection("conversas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var conversa = Conversa(
                        id = doc.id,
                        nome = jo.getString("nome"),
                        materiaMonitoria = jo.getString("materiaMonitoria")
                    )
                    listConversas.add(conversa)
                }

                rvConversas.adapter = conversaAdapter
                rvConversas.layoutManager = LinearLayoutManager(context)

            }
            .addOnFailureListener{e->
                Toast.makeText(activity, "Erro: $e",Toast.LENGTH_LONG).show()
            }
    }


}