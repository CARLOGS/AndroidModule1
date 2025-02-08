package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class VidalComponentActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView;
    lateinit var etUsername: EditText;
    lateinit var btnSend : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_component)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTitle = this.findViewById(R.id.tvTitle)
        tvTitle.text = "This is a text view"

        etUsername = this.findViewById(R.id.etUsername)

        btnSend=this.findViewById(R.id.btnSend);
        btnSend.setOnClickListener{
            Toast.makeText(this, etUsername.text, Toast.LENGTH_SHORT).show()
        }
    }
}