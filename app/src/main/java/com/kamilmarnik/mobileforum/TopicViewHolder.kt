package com.kamilmarnik.mobileforum

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kamilmarnik.mobileforum.model.Topic

class TopicViewAdapter(val topicList: ArrayList<Topic>): RecyclerView.Adapter<TopicViewAdapter.ViewHolder>() {
  class ViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
      val textView = itemView.findViewById<TextView>(R.id.topicView)
  }

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    val v = LayoutInflater.from(p0?.context).inflate(R.layout.list_item,p0,false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int {
    return topicList.size;
  }

  override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
     val topicName = topicList[p1].name
     p0?.textView?.text = topicName
  }
}