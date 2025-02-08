package com.dgtic.androidmodule1.ejercise.home.carlogarcia

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

/**
 * Pantalla inicial del Ejercicio 1
 *
 * Descripción:
 *
 * - Muestra los eventos del ciclo de vida del Activity en el Toast y Logcat
 *
 * @author Carlo García Sánchez
 * fecha: 2025-02-01
 */
class CarloGarciaMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_carlo_garcia_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnLifeCycle = findViewById<Button>(R.id.btnLifeCycle)
        val btnShowInstagram = findViewById<Button>(R.id.btnShowInstagram)
        val btnFirstActivity = findViewById<Button>(R.id.btnFirstActivity)
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Cambio de color de fondo
        btnShowInstagram.setBackgroundColor(Color.argb(255, 0,64,121))
        btnFirstActivity.setBackgroundColor(Color.argb(255, 0,64,121))
        btnLifeCycle.setBackgroundColor(Color.argb(255, 0,64,121))

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Abre el Activity de LifeCicle
        btnLifeCycle.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity::class.java)
            startActivity(intent)
        }

        // Abre URL de Instagram
        btnShowInstagram.setOnClickListener {
            // Logger
            Log.e("Open URL", "Abriendo Instagram")

            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/carlo.garciasanchez.7/"))
            startActivity(Intent.createChooser(urlIntent, "Abrir con:"))
        }

        // Abre la Pantalla irstActivity
        btnFirstActivity.setOnClickListener {
            val intFirstActivity = Intent(this, FirstActivity::class.java).apply {
                // Envía nombre como parámetro para mostrar en el Activity
                putExtra("EXTRA_USER_NAME", "Carlo García Sánchez")
            }

            startActivity(intFirstActivity)
        }
    }
}