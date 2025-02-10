package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/*
    Clase abstracta para implementar el cambio de color de los controles para cada Layout

    Esta clase debe implementarse en cada Lauyout para administrar los colores del Tema
 */
abstract class CGSLayoutManager(contex : AppCompatActivity) {

    // Función abstracta para implementar en cada CGSLauyoutManager
    abstract fun setColor(themeColor : CGSColorData)

    /*
        Función para cambiar el color de los TextViewa y Buttons
     */
    protected fun setThemeColor(views : List<TextView>?, buttons : List<Button>?, themeColor : CGSColorData) {
        // Cambia colores de TextViews
        views?.let {
            for (view in views) {
                themeColor.vbc?.let { view.setBackgroundColor(themeColor.vbc) }
                themeColor.vtc?.let { view.setTextColor(themeColor.vtc) }
            }
        }

        buttons?.let {
            // Cambia colores de Buttons
            for (button in buttons) {
                themeColor.vbc?.let { button.setBackgroundColor(themeColor.vbc) }
                themeColor.vbc?.let { button.setBackgroundColor(themeColor.vbc) }
            }
        }
    }
}