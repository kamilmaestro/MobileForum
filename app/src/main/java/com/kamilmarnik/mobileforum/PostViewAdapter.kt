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
import com.kamilmarnik.mobileforum.model.Post
import com.kamilmarnik.mobileforum.model.Topic
import com.kamilmarnik.mobileforum.service.goTo
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_item.view.*

class PostViewAdapter(private val postList: MutableList<Post>, private val context: Context): RecyclerView.Adapter<PostViewAdapter.PostViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewHolder: Int): PostViewHolder =
    PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_item, parent, false))


  override fun getItemCount(): Int = postList.size

  override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int) {
    val authorName = postList[position].authorLogin
    viewHolder.authorView?.text = authorName
    val postText: String = postList[position].content
    viewHolder.postView?.text = postText
  }

  class PostViewHolder(view: View ) : RecyclerView.ViewHolder(view) {
    val authorView: TextView? = view.findViewById(R.id.authorView)
    val postView: TextView? = view.findViewById(R.id.postView)
  }



}