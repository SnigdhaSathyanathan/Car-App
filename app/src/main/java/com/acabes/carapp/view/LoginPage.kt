package com.acabes.carapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acabes.carapp.R

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        setSupportActionBar(findViewById(R.id.toolbar_login))
        val loginFragment=LoginFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.login_container,loginFragment,"loginFragment")
            .commit()
    }
}