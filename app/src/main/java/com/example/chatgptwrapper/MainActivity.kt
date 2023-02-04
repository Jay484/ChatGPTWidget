package com.example.chatgptwrapper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.support.chatgptwidget.AIChatActivity
import com.support.chatgptwidget.AIChatActivity.Companion.API_KEY

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_open_ai_chat).setOnClickListener {
            val apiKey = findViewById<EditText>(R.id.et_api).text.toString()
            startActivity(
                Intent(this, AIChatActivity::class.java).apply {
                    putExtra(API_KEY, apiKey)
                }
            )
        }
    }
}