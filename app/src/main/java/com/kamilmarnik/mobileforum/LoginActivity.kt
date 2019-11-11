package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kamilmarnik.mobileforum.service.goTo

class LoginActivity : AppCompatActivity() {

  private lateinit var loginBtn: View
  private lateinit var registerBtn: View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    loginBtn = findViewById(R.id.loginBtn)
    registerBtn = findViewById(R.id.newAccountBtn)
    registerBtn.setOnClickListener{ goTo(RegistrationActivity::class.java) }
  }
}
