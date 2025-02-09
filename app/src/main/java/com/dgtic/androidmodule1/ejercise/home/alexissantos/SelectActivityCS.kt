package com.dgtic.androidmodule1.ejercise.home.alexissantos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise1.FlowActivity
import com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2.DidacticActivityCS
import com.dgtic.androidmodule1.ejercise.home.alexissantos.homework.RegisterActivityCS

class SelectActivityCS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cs)

        val btnCurrentFunctionality = findViewById<Button>(R.id.btnCurrentFunctionality)
        btnCurrentFunctionality.setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
        }

        val btnDidacticProject = findViewById<Button>(R.id.btnDidacticProject)
        btnDidacticProject.setOnClickListener {
            val intent = Intent(this, DidacticActivityCS::class.java)
            startActivity(intent)
        }

        val btnHomework = findViewById<Button>(R.id.btnHomework)
        btnHomework.setOnClickListener {
            val intent = Intent(this, RegisterActivityCS::class.java)
            startActivity(intent)
        }
    }
}
