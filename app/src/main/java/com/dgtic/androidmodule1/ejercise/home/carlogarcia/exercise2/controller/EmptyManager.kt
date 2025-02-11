package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.controller

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSColorData
import com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2.model.CGSLayoutManager

class EmptyManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layConstraint : ViewGroup = contex.findViewById<ViewGroup>(R.id.layEmpty)

    override fun init(): CGSLayoutManager {
        return this
    }

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layConstraint.setBackgroundColor(colors.vbc) }
        setThemeColor(null, null, colors)
    }
}