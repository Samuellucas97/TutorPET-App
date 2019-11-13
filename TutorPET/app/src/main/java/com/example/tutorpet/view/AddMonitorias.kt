package com.example.tutorpet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.R
import com.example.tutorpet.model.MonitoriaAgendada
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_monitorias.*
import kotlinx.android.synthetic.main.fragment_monitorias.*
import org.json.JSONObject

class AddMonitorias : AppCompatActivity() {

    var listMonitorias = arrayListOf<String>()

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_monitorias)

        this.lerDados()
    }

    fun lerDados(){

        listMonitorias.clear()

        db.collection("monitoriasAgendadas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var monitoria = jo.getString("nome")
                    listMonitorias.add(monitoria)
                }

                listView.adapter =
                    ArrayAdapter(this,android.R.layout.simple_list_item_1,
                        listMonitorias)

            }
            .addOnFailureListener{e->
                Toast.makeText(this, "Erro: $e", Toast.LENGTH_LONG).show()
            }
    }
}
