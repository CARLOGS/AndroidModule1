package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class LinearManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layLinear : ViewGroup = contex.findViewById<ViewGroup>(R.id.layLinear)

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layLinear.setBackgroundColor(colors.vbc) }
        setThemeColor(null, null, colors)
    }
}