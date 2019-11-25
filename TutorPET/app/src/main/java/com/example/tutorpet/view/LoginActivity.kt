package com.example.tutorpet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tutorpet.view.CadastroActivity
import com.example.tutorpet.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivityForResult(intent, 10)
        }


        btnEntrar.setOnClickListener {
            entrar()
        }
    }

    private fun entrar() {
        var pref = getSharedPreferences("configuracoes",0)
        var login = pref.getString("Email", "")
        var senha = pref.getString("Senha", "")

        edtEmail.setText(login)
        edtSenha.setText(senha)

        login = pref.getString("Email", "")
        senha = pref.getString("Senha", "")

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK){
            data?.let {
                val email: String? = data.getStringExtra("email")
                val senha: String? = data.getStringExtra("senha")

                edtEmail.setText(email)
                edtSenha.setText(senha)

            }
        }
    }

}
