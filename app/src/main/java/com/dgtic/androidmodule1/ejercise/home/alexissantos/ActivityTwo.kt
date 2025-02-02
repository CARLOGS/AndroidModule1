package com.dgtic.androidmodule1.ejercise.home.alexissantos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class ActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val tvReceivedDataTwo = findViewById<TextView>(R.id.tvReceivedDataTwo)
        val btnReturnData = findViewById<Button>(R.id.btnReturnData)

        // Recibir datos de ActivityOne
        val receivedMessage = intent.getStringExtra("message")
        val receivedNumber = intent.getIntExtra("number", 0)
        val receivedBoolean = intent.getBooleanExtra("booleanValue", false)
        val receivedDouble = intent.getDoubleExtra("doubleValue", 0.0)

        val receivedData = """
            Mensaje: $receivedMessage
            Número: $receivedNumber
            Boolean: $receivedBoolean
            Doble: $receivedDouble
        """.trimIndent()

        tvReceivedDataTwo.text = "Datos recibidos:\n$receivedData"

        // Mostrar datos con Toast
        Toast.makeText(this, receivedData, Toast.LENGTH_LONG).show()

        // Enviar datos de regreso a ActivityOne
        btnReturnData.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnMessage", "Datos confirmados desde ActivityTwo")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
