package com.dgtic.androidmodule1.ejercise.home.lissetnoriega

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise1.LissetExercise1MainActivity
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2.LissetExercise2MainActivity
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.homework.LissetAppRegisterActivity

class LissetMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnExercise1 = findViewById<Button>(R.id.btnExercise1)
        btnExercise1.setOnClickListener {
            val exercise1Intent = Intent(this, LissetExercise1MainActivity::class.java)
            startActivity(exercise1Intent)
        }
        val btnExercise2 = findViewById<Button>(R.id.btnExercise2)
        btnExercise2.setOnClickListener {
            val exercise2Intent = Intent(this, LissetExercise2MainActivity::class.java)
            startActivity(exercise2Intent)
        }
        val btnMainTeam = findViewById<Button>(R.id.btnTeamMenu)
        btnMainTeam.setOnClickListener {
            val mainTeamIntent = Intent(this, TeamActivity::class.java)
            startActivity(mainTeamIntent)
        }
        val btnHomework = findViewById<Button>(R.id.btnHomework)
        btnHomework.setOnClickListener {
            val homeworkIntent = Intent(this, LissetAppRegisterActivity::class.java)
            startActivity(homeworkIntent)
        }
    }
}