package com.kamilmarnik.mobileforum.model

import java.time.LocalDateTime

class User(val id: Long,
           val username: String,
           val registeredOn: LocalDateTime,
           val email: String)