package com.kamilmarnik.mobileforum.model

import java.time.LocalDateTime

class Comment (val commentId: Long,
               val content: String,
               val createdOn: String,
               val postId: Long,
               val authorId: Long,
               val authorLogin:String)