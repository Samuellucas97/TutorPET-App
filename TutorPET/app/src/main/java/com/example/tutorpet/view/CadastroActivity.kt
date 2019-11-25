package com.example.tutorpet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tutorpet.R
import com.google.firebase.auth.FirebaseAuth
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


            if(!Patterns.EMAIL_ADDRESS.matcher(edt_email.text.toString()).matches()) {
                edt_email.error = "E-mail inválido"
                return@setOnClickListener
            }


            if (edt_nome.text.isEmpty()) {
                edt_nome.error = "Campo Nome Obrigatório"
            } else if (edt_curso.text.isEmpty()) {
                edt_curso.error = "Campo Curso Obrigatório"
            } else if (edt_email.text.isEmpty()) {
                edt_email.error = "Campo Email Obrigatório"
            } else if (edt_senha.text.isEmpty()) {
                edt_senha.error = "Campo Senha Obrigatório"
            } else {






                auth.createUserWithEmailAndPassword(edt_email.text.toString(),edt_senha.text.toString())
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful){
                            Toast.makeText(this@CadastroActivity,
                                "Usuário Cadastrado com Sucesso",Toast.LENGTH_LONG).show()
                            finish()
                        }else{
                            val resposta = task.exception!!.toString()
                            Toast.makeText(this@CadastroActivity,
                                resposta,Toast.LENGTH_LONG).show()

                        }

                    }


            }

        }


    }
}
