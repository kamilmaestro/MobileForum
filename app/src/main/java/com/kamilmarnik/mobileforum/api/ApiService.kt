package com.kamilmarnik.mobileforum.api

import com.kamilmarnik.mobileforum.api.requests.RegistrationRequest
import com.kamilmarnik.mobileforum.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

  @POST("/user/")
  fun registerUser(@Body register: RegistrationRequest): Call<User>

  @POST("/login")
  fun loginUser(@Header("Authorization") authHeader: String): Call<Void>
}