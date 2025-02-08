package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz.recycleview

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R

class VidalRecyclerViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tvTitle: TextView;
        var rvAnimal: RecyclerView;
        var data = arrayListOf<AnimalEntity>();
        data.add(AnimalEntity("Perro","perrito.jpg","Moteado"));
        data.add(AnimalEntity("Gato","gatito.jpg","Blanco"));
        data.add(AnimalEntity("Ardilla","arrdillitaito.jpg","cafe"));
        data.add(AnimalEntity("Pato","pato.jpg","Negro"));
        data.add(AnimalEntity("Pez","pez.jpg","Dorado"));

        tvTitle = findViewById<TextView>(R.id.tvTitle);
        rvAnimal = findViewById<RecyclerView>(R.id.rvAnimal);

        tvTitle.text = "Listado de Animales: ${data.count()}";

        val adapter = AnimalAdapter(data);
        rvAnimal.adapter = adapter;
        rvAnimal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }
}