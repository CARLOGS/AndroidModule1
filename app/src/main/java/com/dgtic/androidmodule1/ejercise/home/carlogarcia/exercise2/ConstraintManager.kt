package com.dgtic.androidmodule1.ejercise.home.carlogarcia.exercise2

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class ConstraintManager(contex: AppCompatActivity) : CGSLayoutManager(contex) {
    // Layout
    private var layConstraint : ViewGroup = contex.findViewById<ViewGroup>(R.id.layConstraint)

    override fun setColor(colors: CGSColorData) {
        colors.vbc?.let { layConstraint.setBackgroundColor(colors.vbc) }
        setThemeColor(null, null, colors)
    }
}