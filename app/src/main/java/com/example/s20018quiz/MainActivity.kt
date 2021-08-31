package com.example.s20018quiz;

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.s20018quiz.databinding.ActivityMainBinding
import java.util.*
import kotlin.Result

class MainActivity : AppCompatActivity() {

    private var tvCount: TextView? = null
    private var tvQuestion: TextView? = null
    private var ansBtn1: Button? = null
    private var ansBtn2: Button? = null
    private var ansBtn3: Button? = null
    private var ansBtn4: Button? = null
    private var nextBtn: Button? = null
    private var i = 0

    var quizData = arrayOf(
        arrayOf("考えたり計算したりするパソコンの頭脳は？", "CPU", "PCU", "PCR", "CPP"),
        arrayOf("CDやDVDなど、光ディスクを読み書きする装置は？", "光学ドライブ", "光磁気ディスク", "磁気テープ", "フラッシュメモリ"),
        arrayOf("記憶装置のなかで、アクセス時間が短いものは？", "キャッシュメモリ", "HDD", "SSD", "主記憶"),
        arrayOf("プリンタが１分間に印刷できるページ数を表す単位は？", "ppm", "cpi", "dpi", "rpm"),
        arrayOf("2.4GHzの電波を使って無線通信を行う規格は？", "Blutooth", "IrDA", "USB", "IEEE1394"),
        arrayOf("10進数155を2進数で表したものは？", "10011011", "10110011", "11001101", "11011001"),
        arrayOf("2進数10110を3倍したものは？", "111010", "111110", "1000010", "10110000"),
        arrayOf("映像データや音声データの圧縮方式は？", "MPEG", "BMP", "GIF", "JPEG"),
        arrayOf("Apple社製のOSは？", "Apple", "Windows", "Linux", "iphone"),
        arrayOf("無線LANの規格は？", "IEEE802.11n", "CDMA", "IEEE802.3", "ISON")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //idを取得
        id
        showQuiz()
    }

    //idを取得
    val id: Unit
        get() {
            tvCount = findViewById(R.id.Count)
            tvQuestion = findViewById(R.id.Question)
            ansBtn1 = findViewById(R.id.Btn1)
            ansBtn2 = findViewById(R.id.Btn2)
            ansBtn3 = findViewById(R.id.Btn3)
            ansBtn4 = findViewById(R.id.Btn4)
            nextBtn = findViewById(R.id.NextBtn)
        }

    //クイズを表示
    @SuppressLint("SetTextI18n")
    fun showQuiz() {

        //シャッフル
        val num = Arrays.asList(1, 2, 3, 4)
        Collections.shuffle(num)
        tvCount!!.text = "残り" + (9 - i) + "問"
        tvQuestion!!.text = quizData[i][0]
        ansBtn1!!.text = quizData[i][num[0]!!]
        ansBtn2!!.text = quizData[i][num[1]!!]
        ansBtn3!!.text = quizData[i][num[2]!!]
        ansBtn4!!.text = quizData[i][num[3]!!]
    }

    //ボタンが押されたときの正誤判定
    fun onButton(view: View) {
        //押されたボタン
        val clickedBtn = view as Button
        val clickedAns = clickedBtn.text.toString()
        if (clickedAns == quizData[i][1]) {
            clickedBtn.text = "正解！"


            //ボタンを無効化、ネクストボタンを有効化
            ansBtn1!!.isEnabled = false
            ansBtn2!!.isEnabled = false
            ansBtn3!!.isEnabled = false
            ansBtn4!!.isEnabled = false
            nextBtn!!.isEnabled = true

            //i++;
            if (i == 10) {
                //画面遷移
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                finish()

            } else {
                i++
            }
        } else {
            clickedBtn.text = "不正解！"
            tvQuestion!!.text = "ゲームオーバー！"

            //ボタンを無効化
            ansBtn1!!.isEnabled = false
            ansBtn2!!.isEnabled = false
            ansBtn3!!.isEnabled = false
            ansBtn4!!.isEnabled = false
            nextBtn!!.isEnabled = false
        }
    }

    //Nextボタンが押された時の処理
    fun onNext(view: View?) {
        showQuiz()
        ansBtn1!!.isEnabled = true
        ansBtn2!!.isEnabled = true
        ansBtn3!!.isEnabled = true
        ansBtn4!!.isEnabled = true
    }
}