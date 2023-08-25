package com.acabes.carapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acabes.carapp.R

class MainScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val fragment1=CarFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment1,"fragment1")
            .commit()
    }
}