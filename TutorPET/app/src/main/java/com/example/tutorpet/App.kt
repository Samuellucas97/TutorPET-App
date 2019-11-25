package com.example.tutorpet


/// Deverá ser mudado para SharedPreferences

import android.app.Application

class App:Application() {
    companion object {
        lateinit var user:String
    }
}