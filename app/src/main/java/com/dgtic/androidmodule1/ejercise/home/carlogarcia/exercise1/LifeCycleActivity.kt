package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

/**
 * Pantalla para mostrar el ciclo de vida de un Activity
 *
 * Muestra mensajes con Toast de cada evento que se ejecuta
 * en el civlo de vida del Activity
 *
 * @author Carlo García Sánchez
 * fecha 2025-02-05
 */
class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_life_cycle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnClose = findViewById<Button>(R.id.btnClose)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Cambio color del fondo
        btnClose.setBackgroundColor(Color.argb(255, 0,64,121))

        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, Exercise1MainActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            val intent = Intent(this, Exercise1MainActivity::class.java)
            startActivity(intent)

            // Destruye/cierra este Activity
            finish()
        }

        // Muestra mensaje
        Toast.makeText(this, "Entró en onCreate", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onCreate")
    }

    override fun onStart() {
        super.onStart()

        // Muestra mensaje
        Toast.makeText(this, "Entró en onStart", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onStart")
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "Entró en onResume", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onResume")
    }

    override fun onPause() {
        super.onPause()

        // Muestra mensaje
        Toast.makeText(this, "Entró en onPause", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onPause")
    }

    override fun onStop() {
        super.onStop()

        // Muestra mensaje
        Toast.makeText(this, "Entró en onStop", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        // Muestra mensaje
        Toast.makeText(this, "Entró en onDestroy", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        // Muestra mensaje
        Toast.makeText(this, "Entró en onRestart", Toast.LENGTH_SHORT).show()
        // Logger
        Log.e("LifeCicle", "Entró en onRestart")
    }
}