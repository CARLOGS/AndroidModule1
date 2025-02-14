package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller

import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSColorData
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSLayoutManager

class LinearManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private val layLinear : ViewGroup = contex.findViewById<ViewGroup>(R.id.layLinear)
    private val lblRecipe =  contex.findViewById<TextView>(R.id.lblRecipe)
    private val txtContent =  contex.findViewById<TextView>(R.id.txtContent)
    private val txtProcedure =  contex.findViewById<TextView>(R.id.txtProcedure)

    override fun init(): CGSLayoutManager {
        txtContent.text = """
            INGREDIENTES
            
            40 Galletas Marías, molidas
            1 1/4 Barras de mantequilla, fundida (90 g c/u)
            1 Lata de Leche Evaporada CARNATION® CLAVEL®
            1 Lata de Leche Condensada LA LECHERA®
            1 Paquete de queso crema, a temperatura ambiente (190 g)
            1 Cucharada de esencia de vainilla
            1/2 Taza de jugo de limón, colado
            1 Limón, su ralladura
            1 Limón, cortado en rodajas
            1 Galleta María, troceada
        """.trimIndent()

        txtProcedure.text = """
            PREPARACION
            
            1.Para la base, mezcla las galletas con la mantequilla hasta integrar por completo, vierte en el molde para tarta desmontable de 24 cm, cubre el fondo y las paredes hasta formar una base; refrigera por 15 minutos.
            
            2.Para el relleno, licúa la Leche Evaporada CARNATION® CLAVEL®, la Leche Condensada LA LECHERA®, el queso crema, la esencia de vainilla y sin dejar de licuar agrega poco a poco el jugo de limón. Vierte lo que licuaste sobre la base y refrigera hasta que este firme.
            
            3.Desmolda, decora con la ralladura de limón, las rodajas de limón y la galleta troceada. Ofrece.
        """.trimIndent()

        return this
    }

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layLinear.setBackgroundColor(colors.vbc) }
        setThemeColor(listOf(txtContent, txtProcedure, lblRecipe), null, colors)
    }
}