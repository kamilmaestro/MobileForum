package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.RegistrationRequest
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.makeEnqueue
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call

class RegistrationActivity : AppCompatActivity() {

  private lateinit var loginTxt: EditText
  private lateinit var passwordTxt: EditText
  private lateinit var emailTxt: EditText
  private lateinit var registerBtn: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registration)

    loginTxt = findViewById(R.id.loginTxt)
    passwordTxt = findViewById(R.id.passwordTxt)
    emailTxt = findViewById(R.id.emailTxt)
    registerBtn = findViewById(R.id.registerBtn)
    registerBtn.setOnClickListener{ registerUser() }
  }

  private fun registerUser() {
      createNewAccount()
      goTo(LoginActivity::class.java)
  }

  private fun createNewAccount() {
    val register = RegistrationRequest(
      loginTxt.text.toString(), passwordTxt.text.toString(), emailTxt.text.toString())

    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL)).registerUser(register)
    makeEnqueue(call as Call<Any>, applicationContext)
  }
}
