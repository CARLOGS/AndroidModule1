package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.CarloGarciaMainActivity
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSColorData
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSLayoutManager
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller.EmptyManager
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller.FrameManager
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller.LinearManager
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller.RelativeManager
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

/**
 * Ejercicio 2
 *
 * Activity para mostrar los distintos Layouts y controles
 *
 * Layouts implementados:
 * - Cosntraint Layout en el Activity principal
 *
 * @author Carlo García Sánchez
 */
class Exercise2MainActivity : AppCompatActivity() {
    private lateinit var grpThemes : RadioGroup
    private lateinit var chkNotas : CheckBox
    private lateinit var spnLayout : Spinner
    private lateinit var lblNote : TextView
    private lateinit var lblFinalNote : TextView
    private val themes : ArrayList<CGSColorData> = ArrayList<CGSColorData>()
    private val managers : ArrayList<CGSLayoutManager> = ArrayList<CGSLayoutManager>()
    private val layouts : ArrayList<ViewGroup> = ArrayList<ViewGroup>()

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

        // Oculta los layuout
        setVisivility(layouts, null)

        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)

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

        // Selección de Tema en  los Radio Buttons
        grpThemes.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.radLight -> setTheme(themes[0])
                R.id.radBlue -> setTheme(themes[1])
                R.id.radDark -> setTheme(themes[2])
            }
        }

        // Mostrar notas
        chkNotas.setOnCheckedChangeListener { buttonView, isChecked ->
            if ( isChecked ) {
                lblNote.visibility = View.VISIBLE
                lblFinalNote.visibility = View.VISIBLE
            } else {
                // imgUno.visibility = View.INVISIBLE
                lblNote.visibility = View.INVISIBLE
                lblFinalNote.visibility = View.INVISIBLE
            }
        }

        // Spinner
        val data = arrayListOf("<Seleccionar uno>", "FrameLayout", "LinearLayout", "RelativeLayout")
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
                    // EmptyLayout
                    0 -> {
                        setVisivility(layouts, layouts[3])
                        lblNote.text = "Layout vacío"
                    }
                    // FrameLayout
                    1 -> {
                        setVisivility(layouts, layouts[0])
                        lblNote.text = "Uso de FrameLayout"
                    }
                    // LinearLayout
                    2 -> {
                        setVisivility(layouts, layouts[1])
                        lblNote.text = "Uso de LinearLayout"
                    }
                    // RelativeLayout
                    3 -> {
                        setVisivility(layouts, layouts[2])
                        lblNote.text = "Uso de RelativeLayout y RecyclerView"
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
        Cambia VISIBLE / GOME los Layout segun su selección
     */
    private fun setVisivility(layouts : List<ViewGroup>, laySelected : ViewGroup?) {
        for ( viewGroup in layouts ) {
            when (viewGroup) {
                laySelected -> laySelected?.let { viewGroup.visibility = View.VISIBLE }
                else -> viewGroup.visibility = View.GONE
            }
        }
    }

    /*
     * Inicia todos los controles del Activity
     */
    private fun init() {
        // Fondo Azul #33B5E5 (51,181,229)/ #0099CC (0,153)
        val backBlue = Color.argb(255, 0,153,204)
        // Fondo Oscuro
        val backDark = Color.argb(255, 80,80,80)
        // Color boton Azul
        val bckButtonBlue = Color.argb(255, 0,64,121)
        // Color boton Black
        val bckButtonBlack = Color.argb(255, 20,20,20)

        // Declara los colores de cada Tema
        themes.add( CGSColorData(Color.WHITE, Color.BLACK, bckButtonBlue, Color.WHITE) )    // Light Theme
        themes.add( CGSColorData(backBlue, Color.WHITE, bckButtonBlue, Color.WHITE) )       // Blue Theme
        themes.add( CGSColorData(backDark, Color.WHITE, bckButtonBlack, Color.WHITE) )      // Dark Theme

        // LayoutManagers
        managers.add( FrameManager(this).init() )
        managers.add( LinearManager(this).init() )
        managers.add( RelativeManager(this).init() )
        managers.add( EmptyManager(this).init() )

        // Init Componentes
        spnLayout = findViewById<Spinner>(R.id.spnLayout)
        chkNotas = findViewById<CheckBox>(R.id.chkNotas)
        lblNote = findViewById<TextView>(R.id.lblNote)
        lblFinalNote = findViewById<TextView>(R.id.lblFinalNote)

        // Init layouts
        layouts.add(findViewById<ViewGroup>(R.id.layFrame))     // layFrame
        layouts.add(findViewById<ViewGroup>(R.id.layLinear))    // layLinear
        layouts.add(findViewById<ViewGroup>(R.id.layRelative))  // layRelative
        layouts.add(findViewById<ViewGroup>(R.id.layEmpty))// layConstraint

        setVisivility(layouts, layouts[3])

        grpThemes = findViewById<RadioGroup>(R.id.grpThemes)
        grpThemes.check(R.id.radLight)

        chkNotas.isChecked = false
    }
}