package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.RegistrationRequest
import com.kamilmarnik.mobileforum.model.User
import com.kamilmarnik.mobileforum.service.goTo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    registerUser()
  }

  private fun registerUser() {
    registerBtn.setOnClickListener{
      createNewAccount()
      goTo(LoginActivity::class.java)
    }
  }

  private fun createNewAccount() {
    val register = RegistrationRequest(
      loginTxt.text.toString(), passwordTxt.text.toString(), emailTxt.text.toString())

    val retrofit = Retrofit.Builder()
      .baseUrl("http://10.0.2.2:8080/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val apiService = retrofit.create(ApiService::class.java)
    val call = apiService.registerUser(register)
    call.enqueue(object: Callback<User> {
      override fun onFailure(call: Call<User>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
      }
      override fun onResponse(call: Call<User>, response: Response<User>) {
        if(!response.isSuccessful) {
          Toast.makeText(applicationContext, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
          return
        }
      }
    })
  }
}
