package com.kamilmarnik.mobileforum.service

import android.content.Context
import android.util.Base64
import android.widget.Toast
import com.kamilmarnik.mobileforum.R
import com.kamilmarnik.mobileforum.api.requests.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> retrofitBuilder(service: Class<T>, url: String?) : T {
  val retrofit = Retrofit.Builder()
    .baseUrl(url ?: "")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  return retrofit.create(service)
}

fun makeEnqueue(call: Call<Any>, context: Context?) {
  call.enqueue(object : Callback<Any> {
    override fun onFailure(call: Call<Any>, t: Throwable) {
      Toast.makeText(context, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
    }
    override fun onResponse(call: Call<Any>, response: Response<Any>) {
      if(!response.isSuccessful) {
        Toast.makeText(context, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
        return
      }
    }
  })
}