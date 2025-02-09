package com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2.UIComponentAdapterCS


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

        val adapter = UIComponentAdapterCS(uiComponents) { componentName ->
            val intent = Intent(this, DidacticDetailActivityCS::class.java)
            intent.putExtra("COMPONENT_NAME", componentName)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
