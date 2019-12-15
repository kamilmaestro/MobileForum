package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kamilmarnik.mobileforum.api.ApiService
import com.kamilmarnik.mobileforum.model.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopicListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_topic_list)
    getTopics(intent.getStringExtra("authHeader"))
  }

  private fun getTopics(authHeader: String) {
    val retrofit = Retrofit.Builder()
      .baseUrl("http://10.0.2.2:8080/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val apiService = retrofit.create(ApiService::class.java)
    val call = apiService.getTopics(authHeader)

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
