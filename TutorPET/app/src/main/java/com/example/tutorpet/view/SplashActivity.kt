package com.example.tutorpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorpet.view.HomeActivity
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

   val TIME_IN_SPLASH  =  (30000).toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val initInBackground = object  : Thread(){
            override fun run() {
                try {
                    Thread.sleep( TIME_IN_SPLASH)
                    val intent = Intent(baseContext, HomeActivity::class.java)

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
