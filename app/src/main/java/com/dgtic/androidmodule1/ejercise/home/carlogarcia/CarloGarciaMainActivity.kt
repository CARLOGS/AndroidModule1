package com.dgtic.androidmodule1.ejercise.home.carlogarcia

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise1.Exercise1MainActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.Exercise2MainActivity

class CarloGarciaMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carlo_garcia_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnExercise1 = findViewById<Button>(R.id.btnExercise1)
        val btnExercise2 = findViewById<Button>(R.id.btnExercise2)
        val btnHomework = findViewById<Button>(R.id.btnHomework)
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Cambio de color de fondo
        btnExercise1.setBackgroundColor(Color.argb(255, 0,64,121))
        btnExercise2.setBackgroundColor(Color.argb(255, 0,64,121))
        btnHomework.setBackgroundColor(Color.argb(255, 0,64,121))

        btnHomework.isEnabled = false

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Exercise 1
        btnExercise1.setOnClickListener {
            val intent = Intent(this, Exercise1MainActivity::class.java)
            startActivity(intent)
        }

        // Exercise 2
        btnExercise2.setOnClickListener {
            val intent = Intent(this, Exercise2MainActivity::class.java)
            startActivity(intent)
        }
    }
}