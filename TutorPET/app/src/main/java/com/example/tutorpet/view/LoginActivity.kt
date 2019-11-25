package com.example.tutorpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tutorpet.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnEntrar.setOnClickListener {

            if (edtEmail.text.isEmpty() ) {
                edtEmail.error =  "Campo E-mail Obrigatório"

            }
            else if (edtSenha.text.isEmpty()) {
                edtSenha.error =  "Campo Senha Obrigatório"
            }
            else {

                auth.signInWithEmailAndPassword(edtEmail.text.toString(), edtSenha.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, HomeActivity::class.java))
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Autenticação Falhou!!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
            }
        }


    }
}
