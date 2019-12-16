package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.retrofitBuilder
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

    loadTopics(intent.getStringExtra("authHeader"), topicView)
  }

  private fun buildRecView(topicRecView: RecyclerView) {
    topicRecView.setHasFixedSize(true)
    topicRecView.layoutManager = LinearLayoutManager(applicationContext)
    topicRecView.adapter = TopicViewAdapter(topics, applicationContext)
  }

  private fun loadTopics(authHeader: String, topicView: RecyclerView) {
    val call = retrofitBuilder(ApiService::class.java, "http://10.0.2.2:8080/").getTopics(authHeader)

    call.enqueue(object: Callback<List<Topic>> {
      override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
        Toast.makeText(applicationContext, "failure: ".plus(t.message), Toast.LENGTH_LONG).show()
      }
      override fun onResponse(call: Call<List<Topic>>, response: Response<List<Topic>>) {
        if(!response.isSuccessful) {
          Toast.makeText(applicationContext, "response: ".plus(response.code()), Toast.LENGTH_LONG).show()
          return
        }
        showTopics(response, topicView)
      }
    })
  }

  private fun showTopics(response: Response<List<Topic>>, topicView: RecyclerView) {
    response.body()?.forEach { topic ->
      topics.add(Topic(topic.topicId, topic.name, topic.description, topic.createdOn, topic.authorId))
    }

    topicView.adapter = TopicViewAdapter(topics, applicationContext)
  }
}
