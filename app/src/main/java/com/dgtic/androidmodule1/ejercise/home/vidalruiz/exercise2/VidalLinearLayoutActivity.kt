package com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class VidalLinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_linear_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpPageEvents();
    }
    // Función para configurar el botón de registro
    private fun setUpPageEvents() {
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            val intent = Intent(this, VidalExercise2MainActivity::class.java);
            startActivity(intent);
        }
    }
}