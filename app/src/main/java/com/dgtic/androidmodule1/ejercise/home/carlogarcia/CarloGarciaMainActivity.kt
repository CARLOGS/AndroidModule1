package com.dgtic.androidmodule1.ejercise.home.carlogarcia

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.classroom.explicitintent.SecondActivity
import java.util.Date

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
    // Para esperar respuesta del SecondActivity
    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val isMale = result.data?.getBooleanExtra("EXTRA_GENDER", true)
            Toast.makeText(this, "isCorrect: ${if (isMale != null && isMale) "Hombre" else "Mujer"}",Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Género no definido",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_carlo_garcia_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera btnShowData y cambia su color
        val btnShowData = findViewById<Button>(R.id.btnShowData)
        btnShowData.setBackgroundColor(Color.argb(255, 10,60,10))

        // Recupera btnShowData y cambia su color
        val btnOpenInstagram = findViewById<Button>(R.id.btnOpenInstagram)
        btnOpenInstagram.setBackgroundColor(Color.argb(255, 54,13,61))

        // Recupera tvTeamUserName
        val tvTeamUserName = findViewById<TextView>(R.id.tvTeamUserName)

        // Muestra el nombre que se pasó como parámetro desde el TeamActivity
        intent.extras?.let {
            if (it.containsKey("EXTRA_USER_NAME")) {
                val userName = it.getString("EXTRA_USER_NAME")

                tvTeamUserName.text = userName
            }
        }

        // Abre la URL de Instagram
        btnOpenInstagram.setOnClickListener {
            // Logger
            Log.e("Open URL", "Abriendo Instagram")

            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/carlo.garciasanchez.7/"))
            startActivity(Intent.createChooser(urlIntent, "Abrir con:"))
        }

        // Abre la Pantalla de Información
        btnShowData.setOnClickListener {
            // Logger
            Log.e("Show Info", "Mostrando información")

            val infoIntent = Intent(this, InfoActivity::class.java).apply {
                putExtra("EXTRA_NAME", "Carlo García Sánchez")
                putExtra("EXTRA_AGE", 57)
                putExtra("EXTRA_GENDER", "M") // Male
                putExtra("EXTRA_STUDY", "Ingeniero en computación")
                putExtra("EXTRA_BORN_DATE", "1967-09-04")
                putExtra("EXTRA_EXPERIENCE", "20 años")
                putExtra("EXTRA_LANGUAGES", "Java, JavaScript, Python, C#, Basic")
                putExtra("EXTRA_DB", "SQL Server, Oracle, MySQL, MongoDB, DB2")
                putExtra("EXTRA_JOB", "IT Maganer")
            }

            // Solo se manda al segundo Activity sin esperar respuesta
            // startActivity(secondIntent)

            // Con esto se espera un result
            register.launch(infoIntent)
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