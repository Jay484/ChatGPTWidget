package com.support.chatgptwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AIChatActivity : AppCompatActivity() {
    var apiKey: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiKey = intent.getStringExtra(API_KEY)
        setContentView(R.layout.activity_aichat)
    }

    companion object{
        const val API_KEY = "API_KEY"
    }
}