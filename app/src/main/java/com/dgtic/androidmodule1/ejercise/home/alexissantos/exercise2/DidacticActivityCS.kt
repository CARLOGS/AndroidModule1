package com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R

class DidacticActivityCS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_didactic_cs)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewUI)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val uiComponents = listOf(
            "TextView", "Button", "ImageView", "EditText", "CheckBox",
            "RadioButton", "Spinner", "RecyclerView", "FrameLayout",
            "LinearLayout", "RelativeLayout", "ConstraintLayout"
        )



    }
}
