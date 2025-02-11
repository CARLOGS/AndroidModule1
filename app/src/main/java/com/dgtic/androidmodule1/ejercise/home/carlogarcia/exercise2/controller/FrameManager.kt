package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller

import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSColorData
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSLayoutManager

class FrameManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layFrame : ViewGroup = contex.findViewById<ViewGroup>(R.id.layFrame)
    private var lblSale : TextView = contex.findViewById<TextView>(R.id.lblSale)
    private var lblContact : TextView = contex.findViewById<TextView>(R.id.lblContact)

    override fun init(): CGSLayoutManager {
        return this
    }

    override fun setColor(colors : CGSColorData) {
        colors.vbc?.let { layFrame.setBackgroundColor(colors.vbc) }
        setThemeColor(listOf(lblSale, lblContact), null, colors)
    }
}