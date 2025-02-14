package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class LissetSecondInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_second_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnReturnLissetMain = findViewById<Button>(R.id.btnInfoActivity)
        btnReturnLissetMain.setOnClickListener {
            val lissetIntent = Intent(this, LissetInfoActivity::class.java).apply {
                putExtra("EXTRA_SECOND_OPENED", true)
            }
            startActivity(lissetIntent)
        }

        val tvName    = findViewById<TextView>(R.id.tvName)
        val tvAge     = findViewById<TextView>(R.id.tvAge)
        val tvSchool  = findViewById<TextView>(R.id.tvSchool)
        val tvHasBachelorsDegree = findViewById<TextView>(R.id.tvHasBachelorsDegree)
        val tvHeight  = findViewById<TextView>(R.id.tvHeight)


        intent.extras?.let {
            if(it.containsKey("EXTRA_NAME")){
                val name = it.getString("EXTRA_NAME")
                tvName.text = "Nombre: $name"
            }
            if(it.containsKey("EXTRA_AGE")){
                val age = it.getInt("EXTRA_AGE")
                tvAge.text = "Edad: $age años"
            }
            if(it.containsKey("EXTRA_SCHOOL")){
                val school = it.getString("EXTRA_SCHOOL")
                tvSchool.text = "Escuela: $school"
            }
            if(it.containsKey("EXTRA_BACHELORS_DEGREE")){
                val bDegree = it.getBoolean("EXTRA_BACHELORS_DEGREE")
                tvHasBachelorsDegree.text = "¿Tiene título? ${if(bDegree)"si" else "no"}"
            }
            if(it.containsKey("EXTRA_HEIGHT")){
                val height = it.getFloat("EXTRA_HEIGHT")
                tvHeight.text = "Altura: $height"
            }
        }
    }
}