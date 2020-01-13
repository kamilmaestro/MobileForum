package com.kamilmarnik.mobileforum.api

import androidx.paging.PagedList
import com.kamilmarnik.mobileforum.api.requests.CreateComment
import com.kamilmarnik.mobileforum.api.requests.CreatePost
import com.kamilmarnik.mobileforum.api.requests.LoginRequest
import com.kamilmarnik.mobileforum.api.requests.RegistrationRequest
import com.kamilmarnik.mobileforum.model.Comment
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

  @POST("/register")
  fun registerUser(@Body register: RegistrationRequest): Call<User>

  @POST("/login")
  fun loginUser(@Body login: LoginRequest): Call<Void>

  @GET("/topic/")
  fun getTopics(@Header("Authorization") authHeader: String): Call<List<Topic>>

  @GET("/post/topic/{topicId}")
  fun getPostsByTopicId(@Header("Authorization") authHeader: String,
                        @Path("topicId") topicId: Long) : Call<PagedList<Post>>

  @POST("/post/comment/")
  fun addCommentToPost(@Header("Authorization") authHeader: String,
                       @Body createComment: CreateComment) : Call<Comment>

  @POST("topic/post/")
  fun addPostToTopic(@Header("Authorization") authHeader: String,
                     @Body createPost: CreatePost) : Call<Post>

  @GET("/comment/post/{postId}")
  fun getCommentsByPostId(@Header("Authorization") authHeader: String,
                          @Path("postId") postId: Long) : Call<List<Comment>>
}