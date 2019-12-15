package com.kamilmarnik.mobileforum.model

import java.time.LocalDateTime

class Topic(val topicId: Long,
            val name: String,
            val description: String,
            val createdOn: String,
            val authorId: Long)