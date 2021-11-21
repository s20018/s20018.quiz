package com.example.s20018quiz;

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
//import com.example.s20018quiz.databinding.ActivityMainBinding
import com.example.s20018quiz.databinding.ActivityMainBinding
import java.util.*
import kotlin.Result

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startButton()
        }

    }

    private fun startButton() {
        val intent = Intent(this, Quiz_view::class.java)
        startActivity(intent)
    }


}