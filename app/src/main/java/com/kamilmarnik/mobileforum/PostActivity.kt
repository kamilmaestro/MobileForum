package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.kamilmarnik.mobileforum.model.Post
import java.time.LocalDateTime
import java.util.*

class PostActivity : AppCompatActivity() {
  var posts: MutableList<Post> = mutableListOf()
  var posts2: MutableList<Post> = mutableListOf()
  var posts3: MutableList<Post> = mutableListOf()
  var pagesCount: Int = 0
  var currentPageIndex: Int = 0


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_posts)
    val postView = findViewById<RecyclerView>(R.id.postView)
    buildRecView(postView)

    posts.add(Post(1L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd"))
    posts.add(Post(2L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd1"))
    posts.add(Post(3L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd2"))
    posts.add(Post(4L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd3"))

    posts2.add(Post(5L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd4"))
    posts2.add(Post(6L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd5"))
    posts2.add(Post(7L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd6"))
    posts2.add(Post(8L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd7"))

    posts3.add(Post(9L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd8"))
    posts3.add(Post(10L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd9"))
    posts3.add(Post(10L,"asdddddddasddddddddddddddddddddddddddddddddddddddddddddddddd",LocalDateTime.now(),1L,1L,"asd10"))

    postView.adapter = PostViewAdapter(posts, this)
    pagesCount = 3;
    currentPageIndex = 1

    var fButton = findViewById<Button>(R.id.forwardButton)
    fButton.setOnClickListener {
      if(currentPageIndex==1) {
        postView.adapter = PostViewAdapter(posts2, this)
        currentPageIndex++
      }
      else if (currentPageIndex == 2){
        postView.adapter = PostViewAdapter(posts3, this)
        currentPageIndex++
      }
    }

    var bButton = findViewById<Button>(R.id.backButton)
    bButton.setOnClickListener {
      if(currentPageIndex==3) {
        postView.adapter = PostViewAdapter(posts2, this)
        currentPageIndex--
      }
      else if (currentPageIndex == 2){
        postView.adapter = PostViewAdapter(posts, this)
        currentPageIndex--
      }
    }




  }

  private fun buildRecView(postView:RecyclerView) {
    postView.setHasFixedSize(true)
    postView.layoutManager = LinearLayoutManager(applicationContext)
    postView.adapter = PostViewAdapter(posts, applicationContext)
  }



}
