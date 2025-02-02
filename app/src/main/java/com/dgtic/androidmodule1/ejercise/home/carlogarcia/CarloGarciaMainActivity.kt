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
        // TextView para mostrar la oferta seleccionada
        val tvOfferSelected = findViewById<TextView>(R.id.tvOfferSelected)

        // Muestra la oferta seleccionada
        if (result.resultCode == RESULT_OK) {
            val offerSelected = result.data?.getStringExtra("EXTRA_OFFER")
            val salarySelected = result.data?.getFloatExtra("EXTRA_SALARY", 0.0f)

            tvOfferSelected.text = "Resultado: $offerSelected Sueldo: $salarySelected"
        } else {
            tvOfferSelected.text = "Resultado: No sleccionó oferta"
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
        val btnShowInstagram = findViewById<Button>(R.id.btnShowInstagram)
        btnShowInstagram.setBackgroundColor(Color.argb(255, 0,64,121))

        // Recupera btnShowData y cambia su color
        val btnOfferSelect = findViewById<Button>(R.id.btnOfferSelect)
        btnOfferSelect.setBackgroundColor(Color.argb(255, 0,64,121))

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
        btnShowInstagram.setOnClickListener {
            // Logger
            Log.e("Open URL", "Abriendo Instagram")

            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/carlo.garciasanchez.7/"))
            startActivity(Intent.createChooser(urlIntent, "Abrir con:"))
        }

        // Abre la Pantalla de Información
        btnOfferSelect.setOnClickListener {
            // Logger
            Log.e("Show Info", "Mostrando información")

            val languages = arrayOf("Java", "JavaScript", "Python", "C#", "Basic")
            val dbs = arrayOf("SQL Server", "Oracle", "MySQL", "DB2")

            val infoIntent = Intent(this, InfoActivity::class.java).apply {
                putExtra("EXTRA_NAME", "Carlo García Sánchez")
                putExtra("EXTRA_AGE", 57)
                putExtra("EXTRA_GENDER", "M") // Male
                putExtra("EXTRA_STUDY", "Ingeniero en computación")
                putExtra("EXTRA_BORN_DATE", "1967-09-04")
                putExtra("EXTRA_EXPERIENCE", 20) // Años de experiencia
                putExtra("EXTRA_LANGUAGES", languages)
                putExtra("EXTRA_DB", dbs)
                putExtra("EXTRA_JOB", "IT Maganer")
                putExtra("EXTRA_HAS_WORK", false)
            }

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