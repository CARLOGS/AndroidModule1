package com.dgtic.androidmodule1.finalexercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dgtic.androidmodule1.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoggedUserActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etName: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvPasswordStrength: TextView
    private lateinit var btnSave: Button
    private lateinit var btnLogout: Button
    private lateinit var tvLastLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_user)

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

    private fun initComponents() {
        try {
            tvLastLogin = findViewById(R.id.tvLastLogin) ?: throw NullPointerException("tvLastLogin not found")
            etEmail = findViewById(R.id.etEmail) ?: throw NullPointerException("etEmail not found")
            etName = findViewById(R.id.etName) ?: throw NullPointerException("etName not found")
            etPassword = findViewById(R.id.etPassword) ?: throw NullPointerException("etPassword not found")
            etConfirmPassword = findViewById(R.id.etConfirmPassword) ?: throw NullPointerException("etConfirmPassword not found")
            tvPasswordStrength = findViewById(R.id.tvPasswordStrength) ?: throw NullPointerException("tvPasswordStrength not found")
            btnSave = findViewById(R.id.btnSave) ?: throw NullPointerException("btnSave not found")
            btnLogout = findViewById(R.id.btnLogout) ?: throw NullPointerException("btnLogout not found")
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading UI components: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadUserData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val name = sharedPreferences.getString("name", "")
        val lastLoginTimestamp = sharedPreferences.getString("lastLogin", "Never")

        val formattedDate = if (lastLoginTimestamp != "Never") {
            val date = Date(lastLoginTimestamp!!.toLong())
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)
        } else {
            "Never"
        }

        etEmail.setText(email)
        etName.setText(name)
        etPassword.setText("")
        etConfirmPassword.setText("")
        tvLastLogin.text = "Last Login: $formattedDate"
    }

    private fun setListeners() {
        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val strength = getPasswordStrength(s.toString())
                tvPasswordStrength.text = "Password Strength: $strength"
                when (strength) {
                    "Weak" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
                    "Medium" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_orange_dark))
                    "Strong" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSave.setOnClickListener {
            if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", etEmail.text.toString().trim())
            editor.putString("name", etName.text.toString().trim())
            if (etPassword.text.toString().isNotEmpty()) {
                editor.putString("password", etPassword.text.toString().trim())
            }
            editor.apply()

            Toast.makeText(this, "User data updated successfully!", Toast.LENGTH_SHORT).show()

            // Recargar la actividad para reflejar los cambios sin minimizar
            val intent = intent
            finish()
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
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
}
