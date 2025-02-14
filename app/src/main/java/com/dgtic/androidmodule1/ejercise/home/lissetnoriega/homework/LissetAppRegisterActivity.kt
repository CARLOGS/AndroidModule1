package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.homework

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetAppRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword1 = findViewById<EditText>(R.id.etPassword1)
        val etPassword2 = findViewById<EditText>(R.id.etPassword2)
        val spCountry = findViewById<Spinner>(R.id.spCountry)
        val cbConditions = findViewById<CheckBox>(R.id.cbConditions)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        val ibHome = findViewById<ImageButton>(R.id.ibHome)

        //Spinner
        val data = arrayListOf("Selecciona un país", "México", "Argentina", "Colombia", "Ecuador", "Dinamarca", "España")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spCountry.adapter = adapter
        spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemSelected = data[position]

                if (position == 0) {
                    Toast.makeText(
                        this@LissetAppRegisterActivity,
                        "Item no seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LissetAppRegisterActivity,
                        "Item seleccionado: $itemSelected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}