package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.CreateComment
import com.kamilmarnik.mobileforum.api.requests.CreatePost
import com.kamilmarnik.mobileforum.model.Comment
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCommentActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_comment)
    var aButton = findViewById<Button>(R.id.addCommentButton)
    val textInput = findViewById<EditText>(R.id.addCommentTextView)
    aButton.setOnClickListener {
      addComment(intent.getStringExtra("authHeader"),CreateComment(textInput.text.toString(),intent.getLongExtra("postId", -1)))
      finish()
    }
  }

  private fun addComment(authHeader: String, comment: CreateComment) {
    if(comment.postId == -1L || comment.content == ""){
      return
    }

    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL))
      .addCommentToPost(authHeader,comment)

    call.enqueue(object: Callback<Comment> {
      override fun onFailure(call: Call<Comment>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
        println(t.message)
      }
      override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
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
