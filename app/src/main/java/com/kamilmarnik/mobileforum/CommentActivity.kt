package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.kamilmarnik.mobileforum.model.Comment
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.service.goTo
import java.time.LocalDateTime

class CommentActivity : AppCompatActivity() {
  var comments: MutableList<Comment> = mutableListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comment)
    val commentView = findViewById<RecyclerView>(R.id.commentView)
    buildRecView(commentView)

    comments.add(
     Comment(1L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",
        LocalDateTime.now(),1L,1L,"asd")
    )
    comments.add(
      Comment(2L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",
        LocalDateTime.now(),1L,1L,"asd1")
    )
    comments.add(
      Comment(3L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",
        LocalDateTime.now(),1L,1L,"asd2")
    )
    comments.add(
      Comment(4L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",
        LocalDateTime.now(),1L,1L,"asd3")
    )

    commentView.adapter = CommentViewAdapter(comments, this)
    var aButton = findViewById<Button>(R.id.addButton2)
    aButton.setOnClickListener {
      goTo(AddCommentActivity::class.java)
    }
  }

  private fun buildRecView(commentView:RecyclerView) {
    commentView.setHasFixedSize(true)
    commentView.layoutManager = LinearLayoutManager(applicationContext)
    commentView.adapter = CommentViewAdapter(comments, applicationContext)
  }
}
