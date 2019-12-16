package com.kamilmarnik.mobileforum.service

import android.util.Base64
import com.kamilmarnik.mobileforum.api.requests.LoginRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> retrofitBuilder(service: Class<T>, url: String?) : T {
  val retrofit = Retrofit.Builder()
    .baseUrl(url ?: "")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  return retrofit.create(service)
}

fun getAuthHeader(loginRequest: LoginRequest): String {
  val base: String = loginRequest.username + ":" + loginRequest.password

  return "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
}