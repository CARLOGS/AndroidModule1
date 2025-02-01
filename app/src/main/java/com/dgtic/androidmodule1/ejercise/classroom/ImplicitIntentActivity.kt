package com.dgtic.androidmodule1.ejercise.classroom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class ImplicitIntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_implicit_intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSend = findViewById<Button>(R.id.btnSendIntent)
        btnSend.setOnClickListener {
            Toast.makeText(this, "Sending mail ...", Toast.LENGTH_SHORT).show()

            // Para envío de correo predeterminado en Android
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                // putExtra(Intent.EXTRA_EMAIL, "carlogs@hotmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Aviso urgente")
                putExtra(Intent.EXTRA_TEXT, "Este es un mensaje de prueba para un Intent implícito")
            }

//            startActivity(emailIntent)
            startActivity(Intent.createChooser(emailIntent, "Abrir usando:"))
        }
    }
}