package com.example.tutorpet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tutorpet.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_disciplina_tutoria.*
import org.json.JSONObject

class AddDisciplinaTutoria : AppCompatActivity() {

    var listTutorias = arrayListOf<String>()

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_disciplina_tutoria)

        this.lerDados()
        supportActionBar?.title = "Tutorias"
    }

    fun lerDados(){

        listTutorias.clear()

        db.collection("monitoriasAgendadas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var monitoria = jo.getString("nome")
                    listTutorias.add(monitoria)
                }

                listView.adapter =
                    ArrayAdapter(this,android.R.layout.simple_list_item_1,
                        listTutorias)

            }
            .addOnFailureListener{e->
                Toast.makeText(this, "Erro: $e", Toast.LENGTH_LONG).show()
            }
    }
}
