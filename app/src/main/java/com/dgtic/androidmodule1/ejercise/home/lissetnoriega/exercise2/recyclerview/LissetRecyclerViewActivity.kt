package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvReservation = findViewById<RecyclerView>(R.id.rvReservation)
        val data = listOf(ReservationEntity("Mexico","Progreso",3000.50F,1),
            ReservationEntity("Mexico","Guadalajara",3000.50F,1),
            ReservationEntity("Mexico","Monterrey",7000.50F,5),
            ReservationEntity("Mexico","Quéretaro",6000.78F,6),
            ReservationEntity("Mexico","Acapulco",2000.59F,1),
            ReservationEntity("Mexico","Zapopan",4000.39F,3),
            ReservationEntity("Mexico","Mérida",1200.24F,2),
            ReservationEntity("Mexico","Baja California",1300.50F,1),
            ReservationEntity("Mexico","Mexicali",30450.50F,15),
        )
        val adapter = ReservationAdapter(data)
        rvReservation.adapter = adapter
        rvReservation.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
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