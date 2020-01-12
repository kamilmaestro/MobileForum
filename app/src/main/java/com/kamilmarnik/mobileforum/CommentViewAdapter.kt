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
import android.widget.Toast
import com.kamilmarnik.mobileforum.model.Comment
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.goTo
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_item.view.*

class CommentViewAdapter(private val postList: MutableList<Comment>, private val context: Context): RecyclerView.Adapter<CommentViewAdapter.CommentViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewHolder: Int): CommentViewHolder =
    CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false))


  override fun getItemCount(): Int = postList.size

  override fun onBindViewHolder(viewHolder: CommentViewHolder, position: Int) {
    val authorName = postList[position].authorLogin
    viewHolder.authorView?.text = authorName
    val postText: String = postList[position].content
    viewHolder.postView?.text = postText
    if(position==0) {
      viewHolder.authorView?.setBackgroundColor(context.getColor(R.color.colorAccent))
      viewHolder.authorView?.setTextColor(context.getColor(R.color.colorThird))
      viewHolder.postView?.setBackgroundColor(context.getColor(R.color.colorAccent))
      viewHolder.postView?.setTextColor(context.getColor(R.color.colorThird))
    }
  }

  class CommentViewHolder(view: View ) : RecyclerView.ViewHolder(view) {
    val authorView: TextView? = view.findViewById(R.id.authorView2)
    val postView: TextView? = view.findViewById(R.id.commentView)
  }



}