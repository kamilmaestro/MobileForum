package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.retrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_topic_list)
    val topicList = getTopics(intent.getStringExtra("authHeader")) as ArrayList<Topic>
    val topicView = findViewById<RecyclerView>(R.id.topicView)
    val topicAdapter = TopicViewAdapter(topicList)
    topicView.adapter = topicAdapter
  }

  private fun getTopics(authHeader: String) {
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
        val topics: List<Topic>? = response.body()
        var content = ""
        topics?.forEach { topic ->
          content += "Name:" + topic.name
          content += " descr: " + topic.description + ", "
        }
        Toast.makeText(applicationContext, content, Toast.LENGTH_LONG).show()
      }
    })
  }
}
