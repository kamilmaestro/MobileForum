package com.kamilmarnik.mobileforum

import android.app.AlertDialog
import android.content.ClipDescription
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.kamilmarnik.mobileforum.model.Topic

class TopicViewAdapter(private val topicList: MutableList<Topic>, private val context: Context): RecyclerView.Adapter<TopicViewAdapter.TopicViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewHolder: Int): TopicViewHolder =
    TopicViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))


  override fun getItemCount(): Int = topicList.size

  override fun onBindViewHolder(viewHolder: TopicViewHolder, position: Int) {
    val topicName = topicList[position].name
    viewHolder.textView?.text = topicName
    viewHolder.itemView.setOnClickListener{
      showDescription(topicList[position].description)
    }
    viewHolder.textView?.setOnClickListener{
      goToPosts(topicList[position].topicId)
    }
  }

  class TopicViewHolder(view: View ) : RecyclerView.ViewHolder(view) {
    val textView: TextView? = view.findViewById(R.id.topicView)
  }

  private fun showDescription(description: String){
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Description")
      .setMessage(description)
      .setPositiveButton("Back"){ dialog: DialogInterface?, which: Int ->  }
    builder.show()
  }

  private fun goToPosts(id: Long){

  }
}