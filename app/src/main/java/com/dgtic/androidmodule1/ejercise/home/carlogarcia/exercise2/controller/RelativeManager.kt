package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller

import android.graphics.Color
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSColorData
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSLayoutManager
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.NewsEntity

/*
    Clase para mostrar las noticias usando un RelativeLayout
 */
class RelativeManager(val context: AppCompatActivity) : CGSLayoutManager(context) {
    private lateinit var data : List<NewsEntity>
    // Layout
    private var layRelative : ViewGroup = context.findViewById<ViewGroup>(R.id.layRelative)
    private val lblNewsTitle = context.findViewById<TextView>(R.id.lblNewsTitle)
    private val lstNews = context.findViewById<RecyclerView>(R.id.lstNews)
    private val btnPreview = context.findViewById<ImageButton>(R.id.btnPreview)
    private val btnNext = context.findViewById<ImageButton>(R.id.btnNext)
    private val btnSearch = context.findViewById<Button>(R.id.btnSearch)
    private val txtSearch = context.findViewById<EditText>(R.id.txtSearch)
    private var pos : Int = 0

    override fun init(): CGSLayoutManager {
        btnSearch.setBackgroundColor(Color.argb(255, 0,64,121))
        btnSearch.setTextColor(Color.argb(255, 255,255,255))
        btnSearch.maxLines = 1

        // Inicia las noticias en cada NewsEntity
        data = listOf(
            NewsEntity(0, """  
                El asteroide 2024 YR4, con un tamaño aproximado de entre 40 y 100 metros, pasará muy cerca de la Tierra en diciembre de 2032.
                 
                Debido a su tamaño y velocidad, en internet se le ha apodado “el destructor de ciudades”.
                
                Las principales agencias espaciales calculan que hay cerca de un 2% de probabilidades de que impacte en la Tierra.
                """.trimIndent()),
            NewsEntity(1, """
                La empresa de ciberseguridad The Shadowserver Foundation alertó el viernes 7 de febrero por un ataque de fuerza bruta a gran escala que lleva activo hace casi un mes y con el que han llegado a utilizar 2.8 millones de direcciones IP de origen al día en múltiples países incluyendo a México.
                """.trimIndent()),
            NewsEntity(2, """
                El Servicio Geológico de Estados Unidos (USGS) identificó que el sismo de magnitud 7.6, registrado el sábado 8 de febrero a las 18:30 horas, tuvo su epicentro a 209 kilómetros de la costa sur de las Islas Caimán, además dio detalles sobre su origen.

                El movimiento telúrico que ameritó alerta de tsunami se originó por una falla de desgarre en la corteza superficial, cerca del límite convergente entre las placas tectónicas de América del Norte y el Caribe según el USGS.
                """.trimIndent())
        )

        val adapter = NewsAdapter(data)
        lstNews.adapter = adapter
        lstNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        btnPreview.setOnClickListener {
            if ( pos > 0 )
                pos--

            lstNews.scrollToPosition( pos )
        }

        btnNext.setOnClickListener {
            if ( pos < data.size - 1 )
                pos++

            lstNews.scrollToPosition( pos )
        }

        btnSearch.setOnClickListener {
            search()
        }

        txtSearch.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                // Acción al presionar Enter
                search()
                true // Indica que el evento fue manejado
            } else {
                false // Permite que otros eventos continúen
            }
        }

        return this
    }

    private fun search() {
        var finded : Boolean = false

        for ( i in 0..data.size -1 ) {
            if ( data[i].news.uppercase().contains(txtSearch.text.toString().uppercase()) ) {
                pos = i
                lstNews.scrollToPosition( pos )
                finded = true
                break
            }
        }

        if (!finded)
            Toast.makeText(context, "No se encontró el texto en ningún artículo", Toast.LENGTH_SHORT).show()
    }

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layRelative.setBackgroundColor(colors.vbc) }
        setThemeColor(listOf(lblNewsTitle), listOf(btnSearch), colors)
    }
}