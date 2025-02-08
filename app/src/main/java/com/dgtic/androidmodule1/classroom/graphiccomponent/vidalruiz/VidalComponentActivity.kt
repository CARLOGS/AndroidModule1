package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
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
    lateinit var chkExample : CheckBox;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_component)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTitle = this.findViewById<TextView>(R.id.tvTitle);
        etUsername = this.findViewById<EditText>(R.id.etUsername);
        chkExample = this.findViewById<CheckBox>(R.id.chkExample);
        btnSend=this.findViewById<Button>(R.id.btnSend);

        tvTitle.text = "This is a text view";

        chkExample.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Estas de acuerdo: $isChecked", Toast.LENGTH_SHORT).show()
        }



        btnSend.setOnClickListener{
            Toast.makeText(this, "Estas de acuerdo: ${chkExample.isChecked}", Toast.LENGTH_SHORT).show()
        }
    }
}