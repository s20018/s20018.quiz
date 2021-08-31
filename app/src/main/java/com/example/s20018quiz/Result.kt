package com.example.s20018quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }
    private fun home() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
}
}