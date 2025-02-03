package com.dgtic.androidmodule1.ejercise.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.alexissantos.FlowActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.CarloGarciaMainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Pantalla principal para mostrar las opciones iniciales del equipo
 *
 * Descripción:
 * Muesta un botón por participante del equipo y al resionarlos lo
 * manda a la pantalla de cada uno para mostrar la funcionalidades
 * implementadas para el Ejercicio 1 - Activities
 *
 * Participantes:
 *
 * - García Sánchez Carlo
 * - Noriega Domínguez Lisset
 * - Santos Escobar Christian Alexis
 * - Ruíz Vargas Vidal
 *
 * Fecha: 2025-02-01
 */
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

        // Botón para ir al Activity de Carlo
        val btnCarlo = findViewById<Button>(R.id.btnCarlo)
        btnCarlo.setBackgroundColor(Color.argb(255, 0,64,121))
        btnCarlo.setOnClickListener {
            val carloIntent = Intent(this, CarloGarciaMainActivity::class.java).apply {
                // Envía nombre como parámetro para mostrar en el Activity
                putExtra("EXTRA_USER_NAME", "Carlo García Sánchez")
            }

            // Abre CarloGarciaMainActivity sin esperar respuesta
            startActivity(carloIntent)
        }

        // Botón para ir al Activity de Lisset
        val btnLisset = findViewById<Button>(R.id.btnLisset)
        btnLisset.setBackgroundColor(Color.argb(255, 0,64,121))

        // Botón para ir al Activity de Alexis
        val btnAlxis = findViewById<Button>(R.id.btnAlxis)
        btnAlxis.setBackgroundColor(Color.argb(255, 0,64,121))
        btnAlxis.setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir al Activity de Vidal
        val btnVidal = findViewById<Button>(R.id.btnVidal)
        btnVidal.setBackgroundColor(Color.argb(255, 0,64,121))

        //Set current date.
        val tvDate = findViewById<TextView> (R.id.tvDate)
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvDate.text = currentDate
    }
}