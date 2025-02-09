package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

/**
 * Pantalla para mostrar el perfil y ofertas de trabajo
 *
 */
class JobsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtName = findViewById<TextView>(R.id.tvName)
        val txtAge = findViewById<TextView>(R.id.tvAge)
        val txtGender = findViewById<TextView>(R.id.tvGender)
        val tvStudies = findViewById<TextView>(R.id.tvStudies)
        val tvBornDate = findViewById<TextView>(R.id.tvBornDate)
        val tvExperience = findViewById<TextView>(R.id.tvExperience)
        val tvLanguages = findViewById<TextView>(R.id.tvLanguages)
        val tvDbs = findViewById<TextView>(R.id.tvDbs)
        val tvJob = findViewById<TextView>(R.id.tvJob)
        val tvHasWork = findViewById<TextView>(R.id.tvHasWork)
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        val btnSelectOfferOne = findViewById<Button>(R.id.btnSelectOfferOne)
        val btnSelectOfferTwo = findViewById<Button>(R.id.btnSelectOfferTwo)

        btnSelectOfferOne.setBackgroundColor(Color.argb(255,0,64,121))
        btnSelectOfferTwo.setBackgroundColor(Color.argb(255,0,64,121))

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            setResult(RESULT_CANCELED)

            // Destruye/cierra este Activity
            finish()
        }


        intent.extras?.let {
            if (it.containsKey("EXTRA_NAME")) {
                val name = it.getString("EXTRA_NAME")
                txtName.text = name
            }
            if (it.containsKey("EXTRA_AGE")) {
                val age = it.getInt("EXTRA_AGE")
                txtAge.text = age.toString()
            }
            if (it.containsKey("EXTRA_GENDER")) {
                val gender = it.getString("EXTRA_GENDER")
                txtGender.text = gender
            }
            if (it.containsKey("EXTRA_STUDY")) {
                val study = it.getString("EXTRA_STUDY")
                tvStudies.text = study
            }
            if (it.containsKey("EXTRA_BORN_DATE")) {
                val bornDate = it.getString("EXTRA_BORN_DATE")
                tvBornDate.text = bornDate
            }
            if (it.containsKey("EXTRA_EXPERIENCE")) {
                val experience = it.getInt("EXTRA_EXPERIENCE")
                tvExperience.text = experience.toString()
            }
            if (it.containsKey("EXTRA_LANGUAGES")) {
                val languages = it.getStringArray("EXTRA_LANGUAGES")
                tvLanguages.text = languages.contentToString()
            }
            if (it.containsKey("EXTRA_DB")) {
                val dbs = it.getStringArray("EXTRA_DB")
                tvDbs.text = dbs.contentToString()
            }
            if (it.containsKey("EXTRA_JOB")) {
                val job = it.getString("EXTRA_JOB")
                tvJob.text = job
            }
            if (it.containsKey("EXTRA_HAS_WORK")) {
                val hasJob = it.getBoolean("EXTRA_HAS_WORK")
                tvHasWork.text = "${if (hasJob != null && hasJob) "Sí" else "No"}"
            }
        }

        // Regresa Offer One
        btnSelectOfferOne.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("EXTRA_OFFER", "City Banamex")
                putExtra("EXTRA_SALARY", 45000f)
            }

            setResult(RESULT_OK, resultIntent)

            // Destruye/cierra este Activity
            finish()
        }
        // Regresa Offer Two
        btnSelectOfferTwo.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("EXTRA_OFFER", "Google")
                putExtra("EXTRA_SALARY", 60000f)
            }

            setResult(RESULT_OK, resultIntent)

            // Destruye/cierra este Activity
            finish()
        }
    }
}