package com.example.s20018quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val id = intent.getIntExtra("tokuten",0)
    }
}