package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.goTo
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import kotlinx.android.synthetic.main.list_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.collections.MutableList

class TopicListActivity : AppCompatActivity() {

  private var topics: MutableList<Topic> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_topic_list)

    val topicView = findViewById<RecyclerView>(R.id.topicView)
    buildRecView(topicView)

    loadTopics(intent.getStringExtra(getString(R.string.AUTH_HEADER_KEY)), topicView)
  }

  private fun buildRecView(topicRecView: RecyclerView) {
    topicRecView.setHasFixedSize(true)
    topicRecView.layoutManager = LinearLayoutManager(applicationContext)
    topicRecView.adapter = TopicViewAdapter(topics, applicationContext)
  }

  private fun loadTopics(authHeader: String, topicView: RecyclerView) {
    val call = retrofitBuilder(ApiService::class.java, getString(R.string.URL)).getTopics(authHeader)

    call.enqueue(object: Callback<List<Topic>> {
      override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: ".plus(t.message), Toast.LENGTH_LONG).show()
      }
      override fun onResponse(call: Call<List<Topic>>, response: Response<List<Topic>>) {
        if(!response.isSuccessful) {
          if (response.code() == 401) {
            Toast.makeText(applicationContext, R.string.RE_AUTHENTICATION, Toast.LENGTH_LONG).show()
            goTo(LoginActivity::class.java)
          }
          Toast.makeText(applicationContext, "Error: ".plus(response.code()), Toast.LENGTH_LONG).show()
          return
        }
        response.body()?.let { showTopics(it, topicView) }
      }
    })
  }

  private fun showTopics(obtainedTopics: List<Topic>, topicView: RecyclerView) {
    obtainedTopics.forEach { topic ->
      topics.add(Topic(topic.topicId, topic.name, topic.description, topic.createdOn, topic.authorId))
    }

    topicView.adapter = TopicViewAdapter(topics, this)
  }

}
