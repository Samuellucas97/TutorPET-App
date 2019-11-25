package com.example.tutorpet.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorpet.R

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        supportActionBar?.title = "Cadastro no TutorPET"
    }
}
