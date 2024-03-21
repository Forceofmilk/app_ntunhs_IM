package com.example.rockpaperscissorsgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.util.Random


class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    private lateinit var imgView_computerPick: ImageView
    private lateinit var Btn_userPick_rock : ImageButton
    private lateinit var Btn_userPick_paper : ImageButton
    private lateinit var Btn_userPick_scissors : ImageButton
    private lateinit var WinLooseResult : TextView
    private lateinit var TextView_userPick : TextView

    enum class Choice {
        ROCK, PAPER, SCISSORS
    }

    fun playGame(playerChoice: Choice) {
        val choices = Choice.values()
        val computerPick = choices[Random().nextInt(choices.size)]

        if (computerPick == Choice.ROCK) {
            imgView_computerPick.setImageResource(R.drawable.rock)
        }
        if (computerPick == Choice.PAPER) {
            imgView_computerPick.setImageResource(R.drawable.paper)
        }
        if (computerPick == Choice.SCISSORS) {
            imgView_computerPick.setImageResource(R.drawable.scissor)
        }

        when {
            playerChoice == computerPick -> {
                WinLooseResult.setText(R.string.result_tie)

            }
            (playerChoice == Choice.SCISSORS && computerPick == Choice.PAPER) ||
                    (playerChoice == Choice.ROCK && computerPick == Choice.SCISSORS) ||
                    (playerChoice == Choice.PAPER && computerPick == Choice.ROCK) -> {
                        WinLooseResult.setText((R.string.result_win))
                    }
            else -> {
                WinLooseResult.setText(R.string.result_loose)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView_computerPick = findViewById(R.id.imageView_computerPick)
        Btn_userPick_rock = findViewById(R.id.imgBtn_rock)
        Btn_userPick_paper = findViewById(R.id.imgBtn_paper)
        Btn_userPick_scissors = findViewById(R.id.imgBtn_scissors)
        WinLooseResult = findViewById(R.id.result_Text)
        TextView_userPick = findViewById(R.id.textView_userPick)

        imgView_computerPick.setImageResource(R.drawable.choose_default)



        Btn_userPick_rock.setOnClickListener {
            TextView_userPick.setText("You pick ROCK")
            playGame(Choice.ROCK)
        }

        Btn_userPick_paper.setOnClickListener {
            TextView_userPick.setText("You pick PAPER")
            playGame(Choice.PAPER)
        }

        Btn_userPick_scissors.setOnClickListener {
            TextView_userPick.setText("You pick SCISSORS")
            playGame(Choice.SCISSORS)
        }

        }
    }


