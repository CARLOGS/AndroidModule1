package com.dgtic.androidmodule1.ejercise.classroom

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class LifeCicleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show()
        Log.e("LifeCicle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("LifeCicle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("LifeCicle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show()
        Log.e("LifeCicle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("LifeCicle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LifeCicle", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("LifeCicle", "onRestart")
    }
}