package com.dgtic.androidmodule1.ejercise.home.carlogarcia

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

class FirstActivity : AppCompatActivity() {
    // Espera respuesta del InfoActivity
    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Recupera controles
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
        setContentView(R.layout.activity_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnJobSelector = findViewById<Button>(R.id.btnJobSelector)
        val tvTeamUserName = findViewById<TextView>(R.id.tvTeamUserName)
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Cambio color del fondo
        btnJobSelector.setBackgroundColor(Color.argb(255, 0,64,121))

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)

            // Destruye/cierra este Activity
            finish()
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, CarloGarciaMainActivity::class.java)
            startActivity(intent)
        }

        // Muestra el nombre que se pasó como parámetro desde el TeamActivity
        intent.extras?.let {
            if (it.containsKey("EXTRA_USER_NAME")) {
                val userName = it.getString("EXTRA_USER_NAME")

                tvTeamUserName.text = userName
            }
        }

        btnJobSelector.setOnClickListener {
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
    }
}