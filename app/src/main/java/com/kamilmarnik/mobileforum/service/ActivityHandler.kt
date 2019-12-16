package com.kamilmarnik.mobileforum.service

import android.content.Context
import android.content.Intent
import android.os.Bundle

internal fun <T> Context.goTo(it: Class<T>, extras: Bundle.() -> Unit = {}) {
  val intent = Intent(this, it)
  intent.putExtras(Bundle().apply(extras))
  startActivity(intent)
}