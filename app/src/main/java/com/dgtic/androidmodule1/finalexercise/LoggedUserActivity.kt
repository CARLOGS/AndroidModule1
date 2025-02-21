package com.dgtic.androidmodule1.finalexercise

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.databinding.ActivityLoggedUserBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoggedUserActivity : AppCompatActivity() {
    // Recupera el Binding de Fragment
    private lateinit var binding : ActivityLoggedUserBinding

    private val READ_STORAGE_REQUEST = 100
    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el Binding
        binding = ActivityLoggedUserBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_logged_user)

        // Muestra el ActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initComponents()
        loadUserData()
        setListeners()

        // Intenta cargar la imagen
        uri?.let {
            if ( !uri!!.trim().isEmpty() )
                checkStoragePermission()
        }

        // Manejo del botón "Atrás" para cerrar sesión
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                cerrarSesion()
            }
        })
    }

    private fun loadUserData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val lastLoginTimestamp = sharedPreferences.getString("lastLogin", "Never")

        val email = sharedPreferences.getString("email", "")
        val pass = sharedPreferences.getString("password", "")
        val name = sharedPreferences.getString("name", "")
        val kind = sharedPreferences.getString("kind", "")
        val gender = sharedPreferences.getString("gender", "")
        uri = sharedPreferences.getString("uri", "")

        val formattedDate = if (lastLoginTimestamp != "Never") {
            val date = Date(lastLoginTimestamp!!.toLong())
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)
        } else {
            "Never"
        }

        // Solo se hace referencia por el ID para poder actualizar el Text ya que el Binding requiere otro método
        findViewById<TextView>(R.id.tvLastLogin).setText("Last Login: $formattedDate")

        findViewById<EditText>(R.id.etEmail).setText(email)
        findViewById<EditText>(R.id.etName).setText(name)
        findViewById<TextView>(R.id.etGender).setText(gender)
        findViewById<TextView>(R.id.etPassword).setText(pass)
        findViewById<TextView>(R.id.etKind).setText(kind)

//        binding.etConfirmPassword.setText("")
    }

    private fun setListeners() {
        findViewById<EditText>(R.id.etPassword).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val tvPass = findViewById<TextView>(R.id.tvPasswordStrength)
                tvPass.visibility = View.VISIBLE

                val strength = getPasswordStrength(s.toString())
                tvPass.setText("Password Strength: $strength")

                when (strength) {
                    "Weak" -> tvPass.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
                    "Medium" -> tvPass.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_orange_dark))
                    "Strong" -> tvPass.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            //if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
//            if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
//                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", findViewById<EditText>(R.id.etEmail).text.toString().trim())
            editor.putString("name", findViewById<EditText>(R.id.etName).text.toString().trim())
            if (binding.etPassword.text.toString().isNotEmpty()) {
                editor.putString("password", findViewById<EditText>(R.id.etPassword).text.toString().trim())
            }
            editor.apply()

            Toast.makeText(this, "User data updated successfully!", Toast.LENGTH_SHORT).show()

            finish()
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            cerrarSesion()
        }
    }

    private fun getPasswordStrength(password: String): String {
        return when {
            password.length >= 10 && password.matches(".*[A-Z].*".toRegex()) && password.matches(".*\\d.*".toRegex()) -> "Strong"
            password.length >= 6 -> "Medium"
            else -> "Weak"
        }
    }

    private fun cerrarSesion() {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentTime = System.currentTimeMillis().toString()
        editor.putString("lastLogin", currentTime)
        editor.apply()

//        val intent = Intent(this, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)

        finish()
    }

    private fun initComponents() {
        try {
            binding.tvLastLogin.text ?: throw NullPointerException("tvLastLogin not found")
            binding.etEmail.text ?: throw NullPointerException("etEmail not found")
            binding.etName.text ?: throw NullPointerException("etName not found")
            binding.etPassword.text ?: throw NullPointerException("etPassword not found")
//            binding.etConfirmPassword.text ?: throw NullPointerException("etConfirmPassword not found")
            binding.tvPasswordStrength.text ?: throw NullPointerException("tvPasswordStrength not found")
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading UI components: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Checa permisos para leer imagenes locales
    fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_REQUEST)
        } else {
            // Permiso ya concedido
            findViewById<ImageView>(R.id.imgPhoto).setImageURI(Uri.parse(uri))
        }
    }

    // Sobre carga el método para regresar en el icono home
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
