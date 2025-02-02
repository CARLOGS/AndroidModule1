package com.dgtic.androidmodule1.ejercise.home.alexissantos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class ActivityOne : AppCompatActivity() {

    // Nuevo lanzador de actividad
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val returnMessage = data?.getStringExtra("returnMessage")
                findViewById<TextView>(R.id.tvReceivedDataOne).text = "Datos recibidos: $returnMessage"
                Toast.makeText(this, "Mensaje recibido: $returnMessage", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val tvReceivedDataOne = findViewById<TextView>(R.id.tvReceivedDataOne)
        val btnOpenActivityTwo = findViewById<Button>(R.id.btnOpenActivityTwo)

        // Recibir datos de ActivityTwo si existen
        val receivedMessage = intent.getStringExtra("returnMessage")
        if (receivedMessage != null) {
            tvReceivedDataOne.text = "Datos recibidos: $receivedMessage"
            Toast.makeText(this, "Mensaje recibido: $receivedMessage", Toast.LENGTH_LONG).show()
        }

        // Enviar datos a ActivityTwo
        btnOpenActivityTwo.setOnClickListener {
            val intent = Intent(this, ActivityTwo::class.java)
            intent.putExtra("message", "Hola desde ActivityOne")
            intent.putExtra("number", 42)
            intent.putExtra("booleanValue", true)
            intent.putExtra("doubleValue", 99.99)
            activityResultLauncher.launch(intent) // Reemplazo de startActivityForResult()
        }
    }
}
