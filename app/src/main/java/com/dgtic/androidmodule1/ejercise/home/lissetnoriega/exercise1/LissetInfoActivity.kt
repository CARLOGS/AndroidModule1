package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class LissetInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSecondActivity= findViewById<Button>(R.id.btnActivity2)
        btnSecondActivity.setOnClickListener {
            val secondActivityIntent = Intent(this, LissetSecondInfoActivity::class.java).apply {
                putExtra("EXTRA_NAME", "Lisset")
                putExtra("EXTRA_AGE", 26)
                putExtra("EXTRA_SCHOOL", "UNAM")
                putExtra("EXTRA_BACHELORS_DEGREE", false)
                putExtra("EXTRA_HEIGHT", 1.64f)
            }
            startActivity(secondActivityIntent)
        }
        val tvSecondOpen  = findViewById<TextView>(R.id.tvSecondOpen)
        intent.extras?.let {
            if (it.containsKey("EXTRA_SECOND_OPENED")) {
                val secondOpened = it.getBoolean("EXTRA_SECOND_OPENED")
                tvSecondOpen.text = "La segunda activity ya se ha abierto? ${if(secondOpened)"si" else "no"}"
            }
        }
        val btnReturnLissetMain = findViewById<Button>(R.id.btnMain)
        btnReturnLissetMain.setOnClickListener {
            val lissetIntent = Intent(this, LissetExercise1MainActivity::class.java)
            startActivity(lissetIntent)
        }
    }

    private fun putExtra(s: String, s1: String) {

    }
}