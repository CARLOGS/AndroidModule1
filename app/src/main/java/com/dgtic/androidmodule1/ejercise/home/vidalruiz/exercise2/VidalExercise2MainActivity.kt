package com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.VidalHomeMainActivity
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2.RecyclerView.VidalRecyclerViewActivity

class VidalExercise2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_exercise2_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpPageEvents();
    }

    // Move between pages.
    private fun setUpPageEvents() {
        val btnFrameLayout = findViewById<Button>(R.id.btnFrameLayout)
        btnFrameLayout.setOnClickListener {
            val intent = Intent(this, VidalFrameLayoutActivity::class.java);
            startActivity(intent);
        }

        val btnLinearLayout = findViewById<Button>(R.id.btnLinearLayout)
        btnLinearLayout.setOnClickListener {
            val intent = Intent(this, VidalLinearLayoutActivity::class.java);
            startActivity(intent);
        }

        val btnRelativeLayout = findViewById<Button>(R.id.btnRelativeLayout)
        btnRelativeLayout.setOnClickListener {
            val intent = Intent(this, VidalRelativeLayoutActivity::class.java);
            startActivity(intent);
        }
        val btnConstrainLayout = findViewById<Button>(R.id.btnConstrainLayout)
        btnConstrainLayout.setOnClickListener {
            val intent = Intent(this, VidalConstrainLayoutActivity::class.java);
            startActivity(intent);
        }

        // Button to return to MainActivity (Home)
        val btnReturnMainPage = findViewById<Button>(R.id.btnReturnMainPage)
        btnReturnMainPage.setOnClickListener {
            val intent = Intent(this, VidalHomeMainActivity::class.java)
            startActivity(intent)
        }
        // Button to return to MainActivity (Home)
        val bntRecycleView = findViewById<Button>(R.id.bntRecycleView)
        bntRecycleView.setOnClickListener {
            val intent = Intent(this, VidalRecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}