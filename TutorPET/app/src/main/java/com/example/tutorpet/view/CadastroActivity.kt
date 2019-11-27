package com.example.tutorpet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tutorpet.R
import com.example.tutorpet.model.Conversa
import com.example.tutorpet.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.title = "Cadastro no TutorPET"

        auth = FirebaseAuth.getInstance()

        btnConfirmar.setOnClickListener {
            registrar()
        }
    }

    private fun registrar() {

        val pref = getSharedPreferences("configuracoes",0)
        val edt = pref.edit()


        if (edt_nome.text.isEmpty()) {
            edt_nome.error = "Campo Nome Obrigatório"
        }
        else if (edt_curso.text.isEmpty()) {
            edt_curso.error = "Campo Curso Obrigatório"
        }
        else if (edt_email.text.isEmpty()) {
            edt_email.error = "Campo Email Obrigatório"
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(edt_email.text.toString()).matches()) {
            edt_email.error = "E-mail inválido"
            return
        }
        else if (edt_senha.text.isEmpty()) {
            edt_senha.error = "Campo Senha Obrigatório"
        }
        else if (edt_senha.text.toString().length < 6) {
            edt_senha.error = "A Senha deve possuir no mínimo 6 caracteres"
        }
        else {

            val email = edt_email.text.toString()
            val senha = edt_senha.text.toString()

            edt.putString("Email", email)
            edt.putString("Senha", senha)
            edt.putBoolean("ehpetiano", switchEhPetiano.isChecked)
            edt.commit()

            val intent: Intent = Intent()
            intent.putExtra("email", email)
            intent.putExtra("senha", senha)

            setResult(RESULT_OK,intent)

            auth.createUserWithEmailAndPassword(edt_email.text.toString(),edt_senha.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){

                        salvandoUsuarioNoFirebase()

                        Toast.makeText(this@CadastroActivity,
                            "Usuário cadastrado com sucesso",Toast.LENGTH_SHORT).show()


                        finish()
                    }else{
                        val resposta = "Falha na criação do usuário: " + task.exception!!.toString()
                        Toast.makeText(this@CadastroActivity,
                            resposta,Toast.LENGTH_LONG).show()

                    }

                }

        }
    }

    private fun salvandoUsuarioNoFirebase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""

        var tipoDeUsuario = ""

        var user = hashMapOf<String,Any>()

        if( switchEhPetiano.isChecked ) {
            tipoDeUsuario = "petiano"

            user = hashMapOf(
                "uid" to uid,
                "nome " to edt_nome.text.toString(),
                "curso" to edt_curso.text.toString(),
                "disciplinas" to arrayListOf<String>(),
                "ehpetiano" to switchEhPetiano.isChecked.toString()
            )
        }
        else
        {
            tipoDeUsuario = "users"


            user = hashMapOf("uid" to uid,
                "uid" to uid,
                "nome " to edt_nome.text.toString(),
                "curso" to edt_curso.text.toString(),
                "disciplinas" to arrayListOf<String>(),
                "ehpetiano" to switchEhPetiano.isChecked.toString(),
                "monitoriasAgendadas" to hashMapOf<String,Any>()
        )}

        val ref = FirebaseFirestore.getInstance().collection(tipoDeUsuario).document(uid)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(
                    this,
                    "Adicionado com sucesso!",
                    Toast.LENGTH_LONG).show()

                //lerDados() //
            }
            .addOnFailureListener {e ->
                Toast.makeText(
                    this,
                    "Erro ao Adicionado: $e",
                    Toast.LENGTH_LONG).show()
            }


    }



}
