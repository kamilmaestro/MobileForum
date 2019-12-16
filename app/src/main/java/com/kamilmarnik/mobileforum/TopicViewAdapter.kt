package com.kamilmarnik.mobileforum

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kamilmarnik.mobileforum.model.Topic

class TopicViewAdapter(private val topicList: MutableList<Topic>, private val context: Context): RecyclerView.Adapter<TopicViewAdapter.TopicViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewHolder: Int): TopicViewHolder =
    TopicViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

  override fun getItemCount(): Int = topicList.size

  override fun onBindViewHolder(viewHolder: TopicViewHolder, position: Int) {
    val topicName = topicList[position].name
    viewHolder.textView?.text = topicName
  }

  class TopicViewHolder(view: View ) : RecyclerView.ViewHolder(view) {
    val textView: TextView? = view.findViewById(R.id.topicView)
  }
}