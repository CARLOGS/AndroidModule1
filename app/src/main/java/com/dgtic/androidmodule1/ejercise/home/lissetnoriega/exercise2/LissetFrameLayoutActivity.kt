package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetFrameLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_frame_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val ibHome = findViewById<ImageButton>(R.id.ibHome)
        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
        val btnGuess = findViewById<Button>(R.id.btnGuess)
        btnGuess.setOnClickListener {
            val selectedButton = rgOptions.checkedRadioButtonId
            if(selectedButton != -1){
                val rbNumber = findViewById<RadioButton>(selectedButton)
                val selectedNumber = rbNumber.text.toString().toInt()
                if(selectedNumber == 5){
                    Toast.makeText(this, "Numero $selectedNumber, ganador", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Numero $selectedNumber, perdedor", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "No se seleccionó nada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}