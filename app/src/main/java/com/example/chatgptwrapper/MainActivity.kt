package com.example.chatgptwrapper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.support.chatgptwidget.AIChatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_open_ai_chat).setOnClickListener {
            startActivity(
                Intent(this, AIChatActivity::class.java)
            )
        }
    }
}