package com.task.stringerlisttest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.stringerlisttest.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
            finish()
        }
    }
}