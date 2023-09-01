package com.acabes.carapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.acabes.carapp.R
import com.acabes.carapp.model.LoginResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class MainScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setContentView(R.layout.activity_main_screen)
        val profileView=findViewById<ImageView>(R.id.account)
        val sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val finalData=sharedPreferences?.getString("data",null)
        val values = Gson().fromJson(finalData,LoginResponse::class.java)
        Picasso.get()
            .load(values.image)
            .placeholder(R.drawable.profile_icon)
            .error(R.drawable.profile_icon)
            .into(profileView)
        val fragment1=CarFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment1,"fragment1")
            .commit()
        intent= Intent()
        profileView.setOnClickListener{
            val profileFragment=ProfileScreenFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,profileFragment,"profileScreen")
                .addToBackStack(null)
                .commit()
            intent=Intent()
        }
    }
}