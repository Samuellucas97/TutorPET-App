package com.example.tutorpet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorpet.LoginActivity
import com.example.tutorpet.R
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

   val TIME_IN_SPLASH  =  (3000).toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val initInBackground = object  : Thread(){
            override fun run() {
                try {
                    Thread.sleep( TIME_IN_SPLASH)
                    val intent = Intent(baseContext, LoginActivity::class.java)

                    startActivity(intent)

                    finish()
                }catch ( e : Exception){
                    e.printStackTrace()
                }
            }
        }

        initInBackground.start()
    }
}
