package com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2.RecyclerView

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz.recycleview.VidalChessAdapter

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

        val recyclerView = findViewById<RecyclerView>(R.id.rvAperturas)
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }

        val chessOpenings = listOf(
            VidalChessEntity("Gambito de Dama", "@drawable/queen_gambit", "White", 3, "Controla el centro sacrificando un peón", "D30"),
            VidalChessEntity("Defensa Siciliana", "@drawable/sicilian_defense", "Black", 5, "Responde a e4 con c5, buscando contrajuego", "B20"),
            VidalChessEntity("Apertura Ruy López", "@drawable/ruy_lopez", "White", 4, "Desarrolla el alfil temprano y presiona en e5", "C60"),
            VidalChessEntity("Defensa Francesa", "@drawable/french_defense", "Black", 3, "Defiende con e6 y busca contraataques en el centro", "C00"),
            VidalChessEntity("Apertura Italiana", "@drawable/italian_opening", "White", 3, "Rápido desarrollo con c3 y d4, control central", "C50"),
            VidalChessEntity("Defensa Caro-Kann", "@drawable/caro_kann", "Black", 2, "Sólida estructura con c6 y d5", "B10"),
            VidalChessEntity("Ataque Trompowsky", "@drawable/trompowsky_attack", "White", 4, "Rápido desarrollo con Alfil en g5", "A45"),
            VidalChessEntity("Gambito de Rey", "@drawable/kings_gambit", "White", 5, "Juego agresivo sacrificando el peón de f4", "C30"),
            VidalChessEntity("Defensa India de Rey", "@drawable/kings_indian", "Black", 5, "Estructura flexible con g6 y d6", "E60"),
            VidalChessEntity("Defensa Escandinava", "@drawable/scandinavian_defense", "Black", 3, "Juego directo con d5 contra e4", "B01")
        )

        val adapter = VidalChessAdapter(chessOpenings) // `chessOpenings` es la lista con las aperturas
        recyclerView.adapter = adapter


        val button = findViewById<Button>(R.id.btnRetorno)
        button.setOnClickListener {
            finish()
        }
    }
}