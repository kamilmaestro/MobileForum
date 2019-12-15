package com.kamilmarnik.mobileforum.model

import java.time.LocalDateTime

class User(val userId: Long,
           val username: String,
           val registeredOn: LocalDateTime,
           val email: String)