package com.kamilmarnik.mobileforum

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kamilmarnik.mobileforum.service.goTo

class AddCommentActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_comment)
    var aButton = findViewById<Button>(R.id.addCommentButton)
    aButton.setOnClickListener {
      finish()
    }
  }
}
