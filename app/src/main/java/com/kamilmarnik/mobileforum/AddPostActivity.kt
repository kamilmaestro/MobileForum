package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.CreatePost
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_post)
    var aButton = findViewById<Button>(R.id.addPostButton)
    val textInput = findViewById<EditText>(R.id.addPostTextView)

    aButton.setOnClickListener {
      addPost(intent.getStringExtra("authHeader"),CreatePost(textInput.text.toString(),intent.getLongExtra("topicId", -1)))
      finish()
    }
  }

  private fun addPost(authHeader: String, post:CreatePost) {
    if(post.topicId == -1L || post.content == ""){
      return
    }

    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL))
      .addPostToTopic(authHeader,post)

    call.enqueue(object: Callback<Post> {
      override fun onFailure(call: Call<Post>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
        println(t.message)
      }
      override fun onResponse(call: Call<Post>, response: Response<Post>) {
        if(!response.isSuccessful) {
          if (response.code() == 401) {
            Toast.makeText(applicationContext, R.string.RE_AUTHENTICATION, Toast.LENGTH_LONG).show()
            return
          }
          Toast.makeText(applicationContext, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()

          return
        }
      }
    })
  }
}
