package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

class LissetExercise1MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_exercise1_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLifeCycle = findViewById<Button>(R.id.btnLissetLifeCycle)
        btnLifeCycle.setOnClickListener {
            val lifeCycleIntent = Intent(this, LissetLifeCycleActivity::class.java)
            startActivity(lifeCycleIntent)
        }
        val btnMoodle = findViewById<Button>(R.id.btnMoodle)
        btnMoodle.setOnClickListener {
            val urlIntent =  Intent(Intent.ACTION_VIEW, Uri.parse("https://dcd.tic.unam.mx/cursosadistancia/"))
            startActivity(Intent.createChooser(urlIntent,"Abrir usando:"))
        }
        val btnMainMenu = findViewById<Button>(R.id.btnMain)
        btnMainMenu.setOnClickListener {
            val mainMenuIntent = Intent(this, TeamActivity::class.java)
            startActivity(mainMenuIntent)
        }
        val btnActivities = findViewById<Button>(R.id.btnActivities)
        btnActivities.setOnClickListener {
            val activitiesIntent = Intent(this, LissetInfoActivity::class.java)
            startActivity(activitiesIntent)
        }
    }
}