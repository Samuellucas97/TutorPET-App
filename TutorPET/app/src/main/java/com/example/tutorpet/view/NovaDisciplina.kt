package com.example.tutorpet.view


import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorpet.R
import com.example.tutorpet.model.Disciplina
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_disciplina_tutoria.*
import kotlinx.android.synthetic.main.activity_add_disciplina_tutoria.listView
import kotlinx.android.synthetic.main.activity_nova_disciplina.*
import org.json.JSONObject

class NovaDisciplina : AppCompatActivity() {


    val db = FirebaseFirestore.getInstance()

    var listDisciplinas = arrayListOf<Disciplina>()

    lateinit var disciplina: Disciplina

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_disciplina)


        listView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            disciplina = listDisciplinas[position]
            edt_nome.setText(disciplina.nome)
           // Log.d("d", disciplina.nome)

        }




        btn_editar.setOnClickListener {
            val u = mapOf(
                "nome" to edt_nome.text.toString()
            )

            db.collection("users").document(disciplina.id)
                .update(u)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Editado com sucesso!",
                        Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {e ->
                    Toast.makeText(
                        this,
                        "Erro ao Editar: $e",
                        Toast.LENGTH_LONG).show()
                }

            lerDados()
        }





        btn_excluir.setOnClickListener {
            db.collection("disciplinas").document(disciplina.id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Deletado com sucesso!",
                        Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {e ->
                    Toast.makeText(
                        this,
                        "Erro ao Deletar: $e",
                        Toast.LENGTH_LONG).show()
                }

            lerDados()


        }

        btn_listar.setOnClickListener {
            lerDados()
        }


        btn_cadastrar.setOnClickListener {

            val disciplina = hashMapOf(
                "nome" to edt_nome.text.toString())

            db.collection("disciplinas")
                .add(disciplina)
                .addOnSuccessListener { docref ->
                    Toast.makeText(this,
                        "Adicionado com Sucesso: ${docref.id}", Toast.LENGTH_LONG)
                        .show()
                }
                .addOnFailureListener{
                        e ->
                    Toast.makeText(this,
                        "Erro ao adicionar: $e", Toast.LENGTH_LONG)
                        .show()
                }

            edt_nome.text.clear()
            edt_nome.requestFocus()

            lerDados()

        }

    }





    fun lerDados(){

        listDisciplinas.clear()

        db.collection("disciplinas")
            .get()
            .addOnSuccessListener {result ->

                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var disciplina = Disciplina(
                        doc.id,
                        jo.getString("nome"))
                    listDisciplinas.add((disciplina))
                }

                listView.adapter =
                    ArrayAdapter(this,android.R.layout.simple_list_item_1,
                        listDisciplinas)

            }
            .addOnFailureListener{e->
                Toast.makeText(this, "Erro: $e",Toast.LENGTH_LONG).show()

            }

    }

}
