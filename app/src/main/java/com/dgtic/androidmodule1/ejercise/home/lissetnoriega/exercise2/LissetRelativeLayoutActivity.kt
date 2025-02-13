package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetRelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_relative_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ibWhatsapp = findViewById<ImageButton>(R.id.ibWhatsapp)
        ibWhatsapp.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.whatsapp.com/"))
            startActivity(Intent.createChooser(whatsappIntent, "Abrir usando:"))
        }
        val ibInstagram = findViewById<ImageButton>(R.id.ibInstagram)
        ibInstagram.setOnClickListener {
            val instagramIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"))
            startActivity(Intent.createChooser(instagramIntent,"Abrir usando: "))
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
    }
}