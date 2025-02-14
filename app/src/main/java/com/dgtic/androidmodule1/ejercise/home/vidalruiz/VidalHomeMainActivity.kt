package com.dgtic.androidmodule1.ejercise.home.vidalruiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise1.VidalExercise1MainActivity
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2.VidalExercise2MainActivity
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.homework.VidalAccountCreationActivity

class VidalHomeMainActivity : AppCompatActivity() {

    private lateinit var btnExercise1: Button
    private lateinit var btnExercise2: Button
    private lateinit var btnHomework: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_home_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        setClickListeners()
    }

    private fun initComponents() {
        btnExercise1 = findViewById(R.id.btnExercise1)
        btnExercise2 = findViewById(R.id.btnExercise2)
        btnHomework = findViewById(R.id.btnHomework)
    }

    private fun setClickListeners() {
        btnExercise1.setOnClickListener { navigateTo(VidalExercise1MainActivity::class.java) }
        btnExercise2.setOnClickListener { navigateTo(VidalExercise2MainActivity::class.java) }
        btnHomework.setOnClickListener { navigateTo(VidalAccountCreationActivity::class.java) }
    }

    private fun navigateTo(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
    }
}
