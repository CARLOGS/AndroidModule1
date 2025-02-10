package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class FrameManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layFrame : ViewGroup = contex.findViewById<ViewGroup>(R.id.layFrame)

    override fun setColor(colors : CGSColorData ) {
        colors.vbc?.let { layFrame.setBackgroundColor(colors.vbc) }
        setThemeColor(null, null, colors)
    }
}