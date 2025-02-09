package com.dgtic.androidmodule1.ejercise.home.alexissantos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise1.FlowActivity // arreglado
import com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2.DidacticActivityCS
import android.view.View

class SelectActivityCS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cs)

        // Aplicar ajustes de UI para los insets
        val mainView = findViewById<View>(R.id.main)
        mainView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Botón para ir a la funcionalidad actual
        val btnCurrentFunctionality = findViewById<Button>(R.id.btnCurrentFunctionality)
        btnCurrentFunctionality.setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java) // arreglado :v
            startActivity(intent)
        }

        // Botón para ir al proyecto didáctico
        val btnDidacticProject = findViewById<Button>(R.id.btnDidacticProject)
        btnDidacticProject.setOnClickListener {
            val intent = Intent(this, DidacticActivityCS::class.java)
            startActivity(intent)
        }
    }
}
