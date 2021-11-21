package com.example.s20018quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import kotlin.collections.ArrayList
//import com.example.s20018.quiz.databinding.ActivityQuizBinding//
import com.example.s20018quiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var quizDate: ArrayList<String> = arrayListOf()
    //正解カウント
    private var i: Int = 0
    //問題数カウント
    private var q: Int = 0

    aa


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val input = InputStreamReader(assets.open("QuizDate.csv"))
        val reader = BufferedReader(input)
        val str = reader.readText()
        val strReader = StringReader(str)
        val csv = CSVIterator(CSVReader(strReader))

        for (row in csv) {
            for (col in row) {
                quizDate.add(col)
            }
        }

        val qusTitle: TextView = binding.questionTitle
        val qus: TextView = binding.question
        val timerView = binding.timer
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4
        val next: Button = binding.nextButton

        next.isEnabled = false
        qusTitle.text = "問題${q + 1}"

        val questionData = arrayOf(
            arrayOf(quizDate[6], quizDate[8], quizDate[9], quizDate[10], quizDate[11]),
            arrayOf(quizDate[12], quizDate[14], quizDate[15], quizDate[16], quizDate[17]),
            arrayOf(quizDate[18], quizDate[20], quizDate[21], quizDate[22], quizDate[23]),
            arrayOf(quizDate[24], quizDate[26], quizDate[27], quizDate[28], quizDate[29]),
            arrayOf(quizDate[30], quizDate[32], quizDate[33], quizDate[34], quizDate[35]),
            arrayOf(quizDate[36], quizDate[38], quizDate[39], quizDate[40], quizDate[41]),
            arrayOf(quizDate[42], quizDate[44], quizDate[45], quizDate[46], quizDate[47]),
            arrayOf(quizDate[48], quizDate[50], quizDate[51], quizDate[52], quizDate[53]),
            arrayOf(quizDate[54], quizDate[56], quizDate[57], quizDate[58], quizDate[59]),
            arrayOf(quizDate[60], quizDate[62], quizDate[63], quizDate[64], quizDate[65]),
        )

        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val second = millisUntilFinished / 1000L % 60L
                timerView.text= second.toString()
            }

            override fun onFinish() {
                next.isEnabled = true
                AlertDialog.Builder(this@QuizActivity)
                    .setTitle("時間切れ")
                    .setPositiveButton("OK", null)
                    .show()

                choice1.isEnabled = false
                choice2.isEnabled = false
                choice3.isEnabled = false
                choice4.isEnabled = false
            }
        }.start()

        //問題文のセット
        qus.text = questionData[q][0]
        //選択肢シャッフル
        val list: List<Int> = listOf(1, 2, 3, 4)
        val num: List<Int> = list.shuffled()
        //表示
        choice1.text = questionData[q][num[0]]
        choice2.text = questionData[q][num[1]]
        choice3.text = questionData[q][num[2]]
        choice4.text = questionData[q][num[3]]

        //正解数セット
        //cnt.text = i.toString()

        choice1.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice1.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice2.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice2.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice3.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice3.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        choice4.setOnClickListener {
            next.isEnabled = true
            timer.cancel()
            if (choice4.text == questionData[q][1]) {
                correct()
            } else{
                incorrect()
            }
        }

        next.setOnClickListener {
            //問題数カウント
            q++
            if (q == 10) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ANSWER", i)
                startActivity(intent)
                finish()
            } else {

                timer.start()
                //qusTitleの表示
                qusTitle.text = "問題${q + 1}"

                //問題文の表示
                qus.text = questionData[q][0]

                val qusNext = list.shuffled()
                choice1.text = questionData[q][qusNext[0]]
                choice2.text = questionData[q][qusNext[1]]
                choice3.text = questionData[q][qusNext[2]]
                choice4.text = questionData[q][qusNext[3]]

                //選択肢の有効化
                choice1.isEnabled = true
                choice2.isEnabled = true
                choice3.isEnabled = true
                choice4.isEnabled = true
                next.isEnabled = false

            }
        }

    }

    private fun correct() {
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4

        AlertDialog.Builder(this)
            .setTitle("正解!!")
            .setPositiveButton("OK", null)
            .show()

        ++i
        choice1.isEnabled = false
        choice2.isEnabled = false
        choice3.isEnabled = false
        choice4.isEnabled = false
    }

    private fun incorrect() {
        val choice1: Button = binding.button1
        val choice2: Button = binding.button2
        val choice3: Button = binding.button3
        val choice4: Button = binding.button4

        AlertDialog.Builder(this)
            .setTitle("不正解...")
            .setPositiveButton("OK", null)
            .show()

        choice1.isEnabled = false
        choice2.isEnabled = false
        choice3.isEnabled = false
        choice4.isEnabled = false
    }
}