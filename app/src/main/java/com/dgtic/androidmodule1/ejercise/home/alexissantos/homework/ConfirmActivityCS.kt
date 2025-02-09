package com.dgtic.androidmodule1.ejercise.home.alexissantos.homework

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class ConfirmActivityCS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_cs)

        val tvInfo = findViewById<TextView>(R.id.tvInfo)

        val nombre = intent.getStringExtra("NOMBRE")
        val apellido = intent.getStringExtra("APELLIDO")
        val correo = intent.getStringExtra("CORREO")
        val sexo = intent.getStringExtra("SEXO")

        tvInfo.text = "Registro exitoso!\n\nNombre: $nombre\nApellido: $apellido\nCorreo: $correo\nSexo: $sexo"
    }
}
