package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.paging.PagedList
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.api.requests.LoginRequest
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class PostActivity : AppCompatActivity() {
  val posts: MutableList<Post> = ArrayList()
  var pagesCount: Int = 0
  var currentPageIndex: Int = 0


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_posts)
    val postView = findViewById<RecyclerView>(R.id.postView)
    buildRecView(postView)

    postView.adapter = PostViewAdapter(posts, this)
    pagesCount = 3
    currentPageIndex = 1

    val fButton = findViewById<Button>(R.id.forwardButton)
    fButton.setOnClickListener {
//      if(currentPageIndex==1) {
//        postView.adapter = PostViewAdapter(posts2, this)
//        currentPageIndex++
//      }
//      else if (currentPageIndex == 2){
//        postView.adapter = PostViewAdapter(posts3, this)
//        currentPageIndex++
//      }
    }

    val bButton = findViewById<Button>(R.id.backButton)
    bButton.setOnClickListener {
//      if(currentPageIndex==3) {
//        postView.adapter = PostViewAdapter(posts2, this)
//        currentPageIndex--
//      }
//      else if (currentPageIndex == 2){
//        postView.adapter = PostViewAdapter(posts, this)
//        currentPageIndex--
//      }
    }

    val aButton = findViewById<Button>(R.id.addButton)
    aButton.setOnClickListener {
      goTo(AddPostActivity::class.java)
    }

    loadPosts(intent.getStringExtra(getString(R.string.AUTH_HEADER_KEY)),
      intent.getLongExtra("topicId", 0),
      postView)
  }

  private fun buildRecView(postView:RecyclerView) {
    postView.setHasFixedSize(true)
    postView.layoutManager = LinearLayoutManager(applicationContext)
    postView.adapter = PostViewAdapter(posts, applicationContext)
  }

  private fun loadPosts(authHeader: String, topicId: Long, postView: RecyclerView) {
    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL))
      .getPostsByTopicId(authHeader, topicId)

    call.enqueue(object: Callback<PagedList<Post>> {
      override fun onFailure(call: Call<PagedList<Post>>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
      }
      override fun onResponse(call: Call<PagedList<Post>>, response: Response<PagedList<Post>>) {
        if(!response.isSuccessful) {
          if (response.code() == 401) {
            Toast.makeText(applicationContext, R.string.RE_AUTHENTICATION, Toast.LENGTH_LONG).show()
            goTo(LoginActivity::class.java)
          }
          Toast.makeText(applicationContext, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
          return
        }
        response.body()?.let { showTopics(it.toList(), postView, authHeader) }
      }
    })
  }

  private fun showTopics(obtainedTopics: List<Post>, topicView: RecyclerView, authHeader: String) {
    obtainedTopics.forEach { post ->
      posts.add(Post(post.postId, post.content, post.createdOn, post.authorId, post.topicId, post.authorLogin))
    }

    topicView.adapter = PostViewAdapter(posts, this)
  }
}
