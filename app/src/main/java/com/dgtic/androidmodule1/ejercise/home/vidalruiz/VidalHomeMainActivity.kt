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

class VidalHomeMainActivity : AppCompatActivity() {
    lateinit var btnExercise1: Button
    lateinit var btnExercise2: Button

    fun initComponent() {
        btnExercise1 = this.findViewById(R.id.btnExercise1);
        btnExercise2 = this.findViewById(R.id.btnExercise2);
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_home_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent();
        btnExercise1.setOnClickListener {
            val intent = Intent(this, VidalExercise1MainActivity::class.java)
            startActivity(intent)
        }
        btnExercise2.setOnClickListener {
            val intent = Intent(this, VidalExercise2MainActivity::class.java)
            startActivity(intent)
        }
    }
}