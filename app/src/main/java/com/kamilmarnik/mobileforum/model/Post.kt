package com.kamilmarnik.mobileforum.model

import java.time.LocalDateTime

class Post(val postId:Long ,
           val content:String,
           val createdOn: LocalDateTime,
           val authorId: Long ,
           val topicId: Long,
           val authorLogin: String )