package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: View
    private lateinit var registerBtn: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn = findViewById(R.id.loginBtn)
        registerBtn = findViewById(R.id.registerBtn)
    }
}
