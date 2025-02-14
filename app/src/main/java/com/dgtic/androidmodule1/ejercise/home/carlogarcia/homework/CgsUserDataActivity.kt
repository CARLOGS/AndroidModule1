package com.dgtic.androidmodule1.ejercise.home.carlogarcia.homework

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

class CgsUserDataActivity : AppCompatActivity() {
    private val READ_STORAGE_REQUEST = 100
    private lateinit var imgPhoto: ImageView
    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_cgs_user_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)
        imgPhoto = findViewById<ImageView>(R.id.imgPhoto)

        val lblName = findViewById<TextView>(R.id.lblName)
        val lblLastName = findViewById<TextView>(R.id.lblLastName)
        val lblGender = findViewById<TextView>(R.id.lblGender)
        val lblAge = findViewById<TextView>(R.id.lblAge)
        val lblMail = findViewById<TextView>(R.id.lblMail)

        // Recupera parámetros
        val name = intent.getStringExtra("EXTRA_NAME")
        name?.let { lblName.text = it }

        val lastname = intent.getStringExtra("EXTRA_LASTNAME")
        lastname?.let { lblLastName.text = it }

        // Género
        val gender = intent.getStringExtra("EXTRA_GENDER")
        gender?.let { lblGender.text = it }

        // Edad
        val age = intent.getStringExtra("EXTRA_AGE")
        age?.let { lblAge.text = it }

        // Mail
        val mail = intent.getStringExtra("EXTRA_MAIL")
        mail?.let { lblMail.text = it }

        // Muestra imagen
        uri = intent.getStringExtra("EXTRA_IMAGE_URI")
        uri?.let {
            if ( !uri!!.isEmpty() )
                checkStoragePermission()
        }

        // Regresa al home
        btnGoHome.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

        // Regresa
        btnBackward.setOnClickListener {
            this.onBackPressed()
//            val intent = Intent(this, CgsLoginActivity::class.java)
//            startActivity(intent)
        }
    }

    fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_REQUEST)
        } else {
            // Permiso ya concedido
            imgPhoto.setImageURI(Uri.parse(uri))
        }
    }

    // Manejar la respuesta del usuario
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imgPhoto.setImageURI(Uri.parse(uri))
//            } else {
//                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}