package com.dgtic.androidmodule1.ejercise.home.alexissantos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        showToast("onCreate")
        Log.d("FlowActivity", "onCreate")

        val btnGoToActivity1 = findViewById<Button>(R.id.btnGoToActivityOne)
        btnGoToActivity1.setOnClickListener {
            val intent = Intent(this, ActivityOne::class.java)
            startActivity(intent)
        }

        val btnOpenBrowser = findViewById<Button>(R.id.btnOpenBrowser)
        btnOpenBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/@androalfa/video/7398283173693574432"))
            startActivity(intent)
        }
    }

    override fun onStart() { super.onStart(); showToast("onStart"); Log.d("FlowActivity", "onStart") }
    override fun onResume() { super.onResume(); showToast("onResume"); Log.d("FlowActivity", "onResume") }
    override fun onPause() { super.onPause(); showToast("onPause"); Log.d("FlowActivity", "onPause") }
    override fun onStop() { super.onStop(); showToast("onStop"); Log.d("FlowActivity", "onStop") }
    override fun onDestroy() { super.onDestroy(); showToast("onDestroy"); Log.d("FlowActivity", "onDestroy") }

    private fun showToast(state: String) {
        Toast.makeText(this, "Estado: $state", Toast.LENGTH_SHORT).show()
    }
}
