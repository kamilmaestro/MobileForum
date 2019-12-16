package com.kamilmarnik.mobileforum.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> retrofitBuilder(service: Class<T>, url: String?) : T {
  val retrofit = Retrofit.Builder()
    .baseUrl(url ?: "")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  return retrofit.create(service)
}