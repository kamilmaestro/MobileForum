package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.model.Comment
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class CommentActivity : AppCompatActivity() {
  var comments: MutableList<Comment> = mutableListOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comment)
    val commentView = findViewById<RecyclerView>(R.id.commentView)
    buildRecView(commentView)
    loadComments(intent.getStringExtra("authHeader"),intent.getLongExtra("postId",0),commentView)


    var aButton = findViewById<Button>(R.id.addButton2)
    aButton.setOnClickListener {
      goTo(AddCommentActivity::class.java){ putString("authHeader", intent.getStringExtra("authHeader")); putLong("postId",intent.getLongExtra("postId",-1))}
      loadComments(intent.getStringExtra("authHeader"),intent.getLongExtra("postId",0),commentView)
    }

    commentView.adapter = CommentViewAdapter(comments, this)
  }

  private fun buildRecView(commentView: RecyclerView) {
    commentView.setHasFixedSize(true)
    commentView.layoutManager = LinearLayoutManager(applicationContext)
    commentView.adapter = CommentViewAdapter(comments, applicationContext)
  }

  private fun loadComments(authHeader: String, postId: Long, commentView: RecyclerView) {
    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL))
      .getCommentsByPostId(authHeader, postId)

    call.enqueue(object : Callback<List<Comment>> {
      override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
        println(t.message)
      }

      override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
        if (!response.isSuccessful) {
          if (response.code() == 401) {
            Toast.makeText(applicationContext, R.string.RE_AUTHENTICATION, Toast.LENGTH_LONG).show()
            goTo(LoginActivity::class.java)
          }
          Toast.makeText(applicationContext, "Error: ".plus(response.code()), Toast.LENGTH_LONG)
            .show()

          return
        }
        response.body()?.let { showComments(it, commentView, authHeader) }
      }
    })
  }

  private fun showComments
        (
    obtainedComments: List<Comment>,
    commentView: RecyclerView,
    authHeader: String
  ) {
    obtainedComments.forEach { comment ->
      comments.add(
        Comment(
          comment.commentId,
          comment.content,
          comment.createdOn,
          comment.postId,
          comment.authorId,
          comment.authorLogin
        )
      )
    }
    commentView.adapter = CommentViewAdapter(comments, this)
  }
}
