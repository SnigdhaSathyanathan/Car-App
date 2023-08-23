package com.acabes.carapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.acabes.carapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler(mainLooper).postDelayed(Runnable {
            val i= Intent(this@MainActivity,MainScreen::class.java)
            startActivity(i)
            finish()
        },3000)
    }
}