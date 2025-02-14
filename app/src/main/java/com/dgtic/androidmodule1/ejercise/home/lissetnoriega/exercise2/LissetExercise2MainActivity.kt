package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2.recyclerview.LissetRecyclerViewActivity

class LissetExercise2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lisset_exercise2_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val ibHome = findViewById<ImageButton>(R.id.ibHome)
        ibHome.setOnClickListener {
            val homeIntent = Intent(this,LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
        val btnFrameLayoutActivity = findViewById<Button>(R.id.btnFrameLayout)
        btnFrameLayoutActivity.setOnClickListener {
            val frameIntent = Intent(this, LissetFrameLayoutActivity::class.java)
            startActivity(frameIntent)
        }
        val btnLinearLayoutActivity = findViewById<Button>(R.id.btnLinearLayout)
        btnLinearLayoutActivity.setOnClickListener {
            val linearIntent = Intent(this, LissetLinearLayoutActivity::class.java)
            startActivity(linearIntent)
        }
        val btnRelativeLayoutActivity = findViewById<Button>(R.id.btnRelativeLayout)
        btnRelativeLayoutActivity.setOnClickListener {
            val relativeIntent = Intent(this, LissetRelativeLayoutActivity::class.java)
            startActivity(relativeIntent)
        }
        val btnConstrainLayoutActivity = findViewById<Button>(R.id.btnConstrainLayout)
        btnConstrainLayoutActivity.setOnClickListener {
            val constrainIntent = Intent(this, LissetConstrainLayoutActivity::class.java)
            startActivity(constrainIntent)
        }
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val registerIntent = Intent(this, LissetRegisterLayoutActivity::class.java)
            startActivity(registerIntent)
        }
        val btnRecyclerView = findViewById<Button>(R.id.btnRecyclerView)
        btnRecyclerView.setOnClickListener {
            val recylcerIntent = Intent(this, LissetRecyclerViewActivity::class.java)
            startActivity(recylcerIntent)
        }
    }
}