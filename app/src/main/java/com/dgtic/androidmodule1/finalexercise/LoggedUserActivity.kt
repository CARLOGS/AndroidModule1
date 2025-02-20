package com.dgtic.androidmodule1.finalexercise

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.databinding.ActivityLoggedUserBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoggedUserActivity : AppCompatActivity() {

    // Recupera el Binding de Fragment
    private lateinit var binding : ActivityLoggedUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_user)

        // Inicializa el Binding
        binding = ActivityLoggedUserBinding.inflate(layoutInflater)

        // Muestra el ActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initComponents()
        loadUserData()
        setListeners()

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
        val name = sharedPreferences.getString("name", "")
        val gender = sharedPreferences.getString("gender", "")
        val pass = sharedPreferences.getString("password", "")

        val formattedDate = if (lastLoginTimestamp != "Never") {
            val date = Date(lastLoginTimestamp!!.toLong())
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)
        } else {
            "Never"
        }

        binding.tvLastLogin.text = "Last Login: $formattedDate"

        email?.let {  binding.etEmail.setText(email) }
        name?.let {  binding.etName.setText(name) }
        gender?.let {  binding.etGender.setText(gender) }
        pass?.let {  binding.etPassword.setText(pass) }

//        binding.etConfirmPassword.setText("")
    }

    private fun setListeners() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val strength = getPasswordStrength(s.toString())
                binding.tvPasswordStrength.text = "Password Strength: $strength"
                when (strength) {
                    "Weak" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
                    "Medium" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_orange_dark))
                    "Strong" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSave.setOnClickListener {
            //if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
//            if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
//                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", binding.etEmail.text.toString().trim())
            editor.putString("name", binding.etName.text.toString().trim())
            if (binding.etPassword.text.toString().isNotEmpty()) {
                editor.putString("password", binding.etPassword.text.toString().trim())
            }
            editor.apply()

            Toast.makeText(this, "User data updated successfully!", Toast.LENGTH_SHORT).show()

            // Recargar la actividad para reflejar los cambios sin minimizar
            val intent = intent
            finish()
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
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

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
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
