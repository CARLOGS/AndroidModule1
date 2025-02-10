package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.CarloGarciaMainActivity
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class Exercise2MainActivity : AppCompatActivity() {
    private lateinit var grpThemes : RadioGroup
    private lateinit var spnLayout : Spinner
    private lateinit var layFrame : ViewGroup
    private lateinit var layLinear : ViewGroup
    private lateinit var layRelative : ViewGroup
    private lateinit var layConstraint : ViewGroup
    private val themes : ArrayList<CGSColorData> = ArrayList<CGSColorData>()
    private val managers : ArrayList<CGSLayoutManager> = ArrayList<CGSLayoutManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise2_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicia controles
        init()

        // Tema inicial
        setTheme(themes[0])

        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

        // Oculta los layuout
        layFrame.visibility = View.GONE
        layConstraint.visibility = View.GONE
        layLinear.visibility = View.GONE

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            val intent = Intent(this, CarloGarciaMainActivity::class.java)
            startActivity(intent)
        }

        // Selección de Tema
        grpThemes.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.radLight -> setTheme(themes[0])
                R.id.radBlue -> setTheme(themes[1])
                R.id.radDark -> setTheme(themes[2])
            }
        }

        // Spinner
        val data = arrayListOf("<Seleccionar uno>", "FrameLayout", "LinearLayout", "RelativeLayout", "ConstraintLayout")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spnLayout.adapter = adapter

        spnLayout.onItemSelectedListener = object: OnItemSelectedListener,
            AdapterView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSelected = data[position]

                when(position) {
                    // Ninguno
                    0 -> {
                        layFrame.visibility = View.GONE
                        layLinear.visibility = View.GONE
                        layRelative.visibility = View.GONE
                        layConstraint.visibility = View.GONE
                    }
                    // FrameLayout
                    1 -> {
                        layFrame.visibility = View.VISIBLE
                        layLinear.visibility = View.GONE
                        layRelative.visibility = View.GONE
                        layConstraint.visibility = View.GONE
                    }
                    // LinearLayout
                    2 -> {
                        layFrame.visibility = View.GONE
                        layLinear.visibility = View.VISIBLE
                        layRelative.visibility = View.GONE
                        layConstraint.visibility = View.GONE
                    }
                    // RelativeLayout
                    3 -> {
                        layFrame.visibility = View.GONE
                        layLinear.visibility = View.GONE
                        layRelative.visibility = View.VISIBLE
                        layConstraint.visibility = View.GONE
                    }
                    // ConstraintLayout
                    4 -> {
                        layFrame.visibility = View.GONE
                        layLinear.visibility = View.GONE
                        layRelative.visibility = View.GONE
                        layConstraint.visibility = View.VISIBLE
                    }
                }
                // Toast.makeText(this@Exercise2MainActivity, "$data[$position]", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setTheme(color: CGSColorData) {
        for ( manager in managers ) {
            manager.setColor(color)
        }
    }

    /*
     * Inicia todos los controles del Activity
     */
    private fun init() {
        // Declara los colores de cada Tema
        themes.add( CGSColorData(Color.WHITE, Color.BLACK, Color.BLUE, Color.BLACK) )
        themes.add( CGSColorData(Color.argb(255, 0,153,204), Color.WHITE, Color.argb(255, 0,64,121), Color.WHITE) )
        themes.add( CGSColorData(Color.argb(255, 80,80,80), Color.WHITE, Color.argb(255, 200,200,200), Color.WHITE) )

        // LayoutManagers
        managers.add( FrameManager(this) )
        managers.add( LinearManager(this) )
        managers.add( RelativeManager(this) )
        managers.add( ConstraintManager(this) )

        // Init Componentes
        spnLayout = findViewById<Spinner>(R.id.spnLayout)
        layFrame = findViewById<ViewGroup>(R.id.layFrame)
        layLinear = findViewById<ViewGroup>(R.id.layLinear)
        layRelative = findViewById<ViewGroup>(R.id.layRelative)
        layConstraint = findViewById<ViewGroup>(R.id.layConstraint)

        grpThemes = findViewById<RadioGroup>(R.id.grpThemes)
        grpThemes.check(R.id.radLight)
    }
}