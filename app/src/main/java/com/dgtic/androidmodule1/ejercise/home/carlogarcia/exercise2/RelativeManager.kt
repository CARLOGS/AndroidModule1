package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class RelativeManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layRelative : ViewGroup = contex.findViewById<ViewGroup>(R.id.layRelative)

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layRelative.setBackgroundColor(colors.vbc) }
        setThemeColor(null, null, colors)
    }
}