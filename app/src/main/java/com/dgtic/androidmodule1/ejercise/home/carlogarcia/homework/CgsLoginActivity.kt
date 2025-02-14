package com.dgtic.androidmodule1.ejercise.home.carlogarcia.homework

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class CgsLoginActivity : AppCompatActivity() {
    private var datUser: String? = ""
    private var datPass: String? = ""
    private var datName: String? = ""
    private var datLastName: String? = ""
    private var datGender: String? = ""
    private var datAge: String? = ""
    private var datMail: String? = ""
    private var datImgUri: String? = ""

    private val regBack = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK){
            datUser = result.data?.getStringExtra("EXTRA_USER")
            datPass = result.data?.getStringExtra("EXTRA_PASS")
            datName = result.data?.getStringExtra("EXTRA_NAME")
            datLastName = result.data?.getStringExtra("EXTRA_LASTNAME")
            datGender = result.data?.getStringExtra("EXTRA_GENDER")
            datAge = result.data?.getStringExtra("EXTRA_AGE")
            datMail = result.data?.getStringExtra("EXTRA_MAIL")
            datImgUri = result.data?.getStringExtra("EXTRA_IMAGE_URI")
        } else {
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cgs_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val txtUser = findViewById<EditText>(R.id.txtUser)
        val txtPass = findViewById<EditText>(R.id.txtPass)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val lblRegister = findViewById<TextView>(R.id.lblRegister)

        // Cambia colores
        btnStart.setTextColor(Color.WHITE)
        btnStart.setBackgroundColor(Color.rgb(0,64,121))

        btnStart.setOnClickListener {
            if (txtUser.text.isNotEmpty()) {
                if ( txtPass.text.isEmpty() )
                    Toast.makeText(this, "Digite la clave", Toast.LENGTH_SHORT).show()
                else
                    validaCredenciales(txtUser.text.toString(), txtPass.text.toString())
            }
        }

        lblRegister.setOnClickListener {
            val intent = Intent(this, CgsRegisterActivity::class.java)

            // Inicia el Activity y espera EXTRA de respuesta
            regBack.launch(intent)
        }

        txtUser.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                true // Indica que el evento fue manejado
            } else {
                false // Permite que otros eventos continúen
            }
        }

        txtPass.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                true // Indica que el evento fue manejado
            } else {
                false // Permite que otros eventos continúen
            }
        }
    }

    private fun validaCredenciales(user :String, pass : String) {
        if ( user.equals(datUser) && pass.equals(datPass) ) {
            val intent = Intent(this, CgsUserDataActivity::class.java).apply {
                putExtra("EXTRA_NAME", datName)
                putExtra("EXTRA_LASTNAME", datLastName)
                putExtra("EXTRA_GENDER", datGender)
                putExtra("EXTRA_AGE", datAge)
                putExtra("EXTRA_MAIL", datMail)
                putExtra("EXTRA_IMAGE_URI", datImgUri)
            }

            // Muesta datos si en correcto el registro
            startActivity(intent)
        } else
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
    }
}