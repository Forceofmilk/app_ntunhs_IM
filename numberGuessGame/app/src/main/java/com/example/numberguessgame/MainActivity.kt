package com.example.numberguessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG:String = MainActivity::class.java.simpleName
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())

        val textView_hint = findViewById<TextView>(R.id.hint)
        val editText_userInput = findViewById<EditText>(R.id.userInput)
        val button_reset = findViewById<Button>(R.id.btn_reset)
        val button_confirm = findViewById<Button>(R.id.btn_confirm)

        var secretNum : Int = Random().nextInt(100)+1
        var guess_max : Int = 100
        var guess_min : Int = 1
        var guessRound : Int = 0

        fun resetGame() {
            Toast.makeText(this, "遊戲已重置",Toast.LENGTH_LONG).show()
            secretNum = Random().nextInt(100)+1
            textView_hint.text = "猜 1 ~ 100 之間的數字"
            guess_max = 100
            guess_min = 1
            guessRound = 0
        }

        button_confirm.setOnClickListener {
            var guessNum = editText_userInput.text.toString().toInt()
            var hintWords : String = ""
            editText_userInput.text.clear()

            if (guessNum == secretNum) {
                hintWords = "厲害! 你猜對囉"
                guessRound++
                AlertDialog.Builder(this).setTitle("恭喜過關").setMessage("終極密碼是 "+secretNum+"\n你總共猜了 "+guessRound+" 次才猜中 !").create().show()
                handler.postDelayed({
                    resetGame()
                    Toast.makeText(this,"5 秒後的操作執行了 ! ",Toast.LENGTH_LONG).show()},
                    6000)


                fun onDestroy() {
                    super.onDestroy()
                    handler.removeCallbacksAndMessages(null)
                }
            }
            else if (guessNum < secretNum) {
                guess_min = guessNum
                hintWords = "太小囉，猜 "+guess_min+" ~ "+guess_max+" 之間的數字"
                guessRound++
            }
            else if (guessNum > secretNum) {
                guess_max = guessNum
                hintWords = "太大囉，猜 "+guess_min+" ~ "+guess_max+" 之間的數字"
                guessRound++
            }
            else{
                hintWords = "請輸入有效數字"
                guessRound++
            }
            textView_hint.text = hintWords
        }

        button_reset.setOnClickListener {
            resetGame()
        }


    }
}