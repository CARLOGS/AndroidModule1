package com.dgtic.androidmodule1.ejercise.home

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_team)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCarlo = findViewById<Button>(R.id.btnCarlo)
        btnCarlo.setBackgroundColor(Color.BLUE)

        val btnLisset = findViewById<Button>(R.id.btnLisset)
        btnLisset.setBackgroundColor(Color.BLUE)

        val btnAlexis = findViewById<Button>(R.id.btnAlxis)
        btnAlexis.setBackgroundColor(Color.BLUE)

        val btnVidal = findViewById<Button>(R.id.btnVidal)
        btnVidal.setBackgroundColor(Color.BLUE)
    }
}