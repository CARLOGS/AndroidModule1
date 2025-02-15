package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.homework

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetSecondRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_second_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra("EXTRA_NAME")
        val lastName = intent.getStringExtra("EXTRA_LAST_NAME")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val gender = intent.getStringExtra("EXTRA_GENDER")
        val country = intent.getStringExtra("EXTRA_COUNTRY")

        val tvSummary = findViewById<TextView>(R.id.tvSummary)
        tvSummary.text = "Nombre: ${name}\nApellido: ${lastName}\nCorreo: ${email}\nGénero: $gender\nPaís: $country"
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val ibHome = findViewById<ImageButton>(R.id.ibHome)
        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}