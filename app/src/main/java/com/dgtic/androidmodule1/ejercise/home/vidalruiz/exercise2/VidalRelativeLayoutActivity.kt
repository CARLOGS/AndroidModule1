package com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class VidalRelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_relative_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadData();
        setUpPageEvents();
    }

    // Función para llenar el Spinner con opciones
    private fun loadData() {
        val spnTipoAcceso = findViewById<Spinner>(R.id.spnTipoAcceso)
        val opciones = arrayOf("Select an option", "Gratuito", "Usuario sin anuncios", "Super Usuario")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spnTipoAcceso.adapter = adapter
    }

    // Función para configurar el botón de registro
    private fun setUpPageEvents() {
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        btnRegistro.setOnClickListener {
            val intent = Intent(this, VidalExercise2MainActivity::class.java);
            startActivity(intent);
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }
    }
}