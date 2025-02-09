package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.CarloGarciaMainActivity

class Exercise2MainActivity : AppCompatActivity() {
    private lateinit var spnLayout : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise2_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Componentes
        spnLayout = findViewById<Spinner>(R.id.spnLayout)
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, CarloGarciaMainActivity::class.java)
            startActivity(intent)
        }

        // Spinner
        val data = arrayListOf("<Seleccionar uno>", "FrameLayout", "LinearLayout", "RelativeLayout", "ConstraintLayout")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spnLayout.adapter = adapter
    }
}