package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.LoginRequest
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.getAuthHeader
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class LoginActivity : AppCompatActivity() {

  private lateinit var loginBtn: View
  private lateinit var registerBtn: View
  private lateinit var loginTxt: EditText
  private lateinit var passwordTxt: EditText

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    loginBtn = findViewById(R.id.loginBtn)
    registerBtn = findViewById(R.id.newAccountBtn)
    loginTxt = findViewById(R.id.loginTxt)
    passwordTxt = findViewById(R.id.passwordTxt)
    loginBtn.setOnClickListener{ login() }
    registerBtn.setOnClickListener{ goTo(RegistrationActivity::class.java) }
  }

  private fun login() {
    val login = LoginRequest(loginTxt.text.toString(), passwordTxt.text.toString())
    val authHeader = getAuthHeader(login)
    val call = retrofitBuilder(ApiService::class.java, "http://10.0.2.2:8080/").loginUser(authHeader)

    call.enqueue(object: Callback<Void> {
      override fun onFailure(call: Call<Void>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
      }
      override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if(!response.isSuccessful) {
          if(response.code() != 404) {
            if (response.code() == 401) {
              Toast.makeText(applicationContext, "Wrong login or password!", Toast.LENGTH_LONG).show()
            }
            return
          }
        }
        goTo(TopicListActivity::class.java) { putString("authHeader", authHeader) }
      }
    })
  }
}
