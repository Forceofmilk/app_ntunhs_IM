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
            Toast.makeText(this, getString(R.string.game_has_been_reset),Toast.LENGTH_LONG).show()
            secretNum = Random().nextInt(100)+1
            textView_hint.text = "1 ~ 100"
            guess_max = 100
            guess_min = 1
            guessRound = 0
        }

        button_confirm.setOnClickListener {
            var guessNum = editText_userInput.text.toString().toInt()
            var hintWords : String = ""
            editText_userInput.text.clear()

            if (guessNum == secretNum) {
                hintWords = getString(R.string.awesome_you_guess_it_right)
                guessRound++
                AlertDialog.Builder(this).setTitle(getString(R.string.congratulations)).setMessage("終極密碼是 "+secretNum+"\n你總共猜了 "+guessRound+" 次才猜中 !").create().show()
                handler.postDelayed({
                    resetGame()
                    Toast.makeText(this, getString(R.string.five_second_reset),Toast.LENGTH_LONG).show()},
                    6000)


                fun onDestroy() {
                    super.onDestroy()
                    handler.removeCallbacksAndMessages(null)
                }
            }
            else if (guessNum < secretNum) {
                guess_min = guessNum
                hintWords = guess_min.toString() + " ~ " + guess_max.toString()
                guessRound++
            }
            else if (guessNum > secretNum) {
                guess_max = guessNum
                hintWords = guess_min.toString() + " ~ " + guess_max.toString()
                guessRound++
            }
            else{
                hintWords = getString(R.string.please_type_in_int_number)
                guessRound++
            }
            textView_hint.text = hintWords
        }

        button_reset.setOnClickListener {
            resetGame()
        }


    }
}