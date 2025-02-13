package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetRegisterLayoutActivity : AppCompatActivity() {
    private lateinit var spCountry : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_register_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Spinner
        val data = arrayListOf("No selección", "México", "Argentina", "Colombia", "Ecuador", "Dinamarca", "España")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spCountry = findViewById(R.id.spCountry)
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
                        this@LissetRegisterLayoutActivity,
                        "Item no seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LissetRegisterLayoutActivity,
                        "Item seleccionado: $itemSelected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val ibHome = findViewById<ImageButton>(R.id.ibHome)
        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}