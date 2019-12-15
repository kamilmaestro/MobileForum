package com.kamilmarnik.mobileforum.service

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kamilmarnik.mobileforum.R
import org.w3c.dom.Text

class ViewAdapter(val topicList: ArrayList<String>): RecyclerView.Adapter<ViewAdapter.ViewHolder>() {
  class ViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
      val textView = itemView.findViewById(R.id.topicView) as TextView
  }

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    val v = LayoutInflater.from(p0?.context).inflate(R.layout.list_item,p0,false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int {
    return topicList.size;
  }

  override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
     val topic = topicList[p1]
     p0?.textView?.text = topic;
  }
}