package com.example.mindo_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        runOnUiThread {
            setContentView(R.layout.activity_welcome)
        }
        val intent=Intent(baseContext,MainActivity().javaClass)
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        val handler = Handler()
        handler.postDelayed({
            // do something after 5000ms
            startActivity(intent)
        }, 3000)



}


}