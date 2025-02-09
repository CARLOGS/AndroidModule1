package com.dgtic.androidmodule1.ejercise.home.alexissantos.homework

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class RegisterActivityCS : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_cs)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        val cbTerminos = findViewById<CheckBox>(R.id.cbTerminos)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.isEnabled = false // Deshabilita el botón al inicio

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validarCampos(etNombre, etApellido, etCorreo, etPassword, rgSexo, cbTerminos, btnRegistrar)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        etNombre.addTextChangedListener(textWatcher)
        etApellido.addTextChangedListener(textWatcher)
        etCorreo.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        cbTerminos.setOnCheckedChangeListener { _, _ ->
            validarCampos(etNombre, etApellido, etCorreo, etPassword, rgSexo, cbTerminos, btnRegistrar)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, ConfirmActivityCS::class.java).apply {
                putExtra("NOMBRE", etNombre.text.toString())
                putExtra("APELLIDO", etApellido.text.toString())
                putExtra("CORREO", etCorreo.text.toString())
                putExtra("SEXO", if (rgSexo.checkedRadioButtonId == R.id.rbMasculino) "Masculino" else "Femenino")
            }
            startActivity(intent)
        }
    }

    private fun validarCampos(
        etNombre: EditText, etApellido: EditText, etCorreo: EditText, etPassword: EditText,
        rgSexo: RadioGroup, cbTerminos: CheckBox, btnRegistrar: Button
    ) {
        val nombre = etNombre.text.toString().trim()
        val apellido = etApellido.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val sexoSeleccionado = rgSexo.checkedRadioButtonId != -1
        val terminosAceptados = cbTerminos.isChecked

        val emailValido = android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        val passwordValida = password.length >= 8

        btnRegistrar.isEnabled = nombre.isNotEmpty() && apellido.isNotEmpty() && emailValido && passwordValida && sexoSeleccionado && terminosAceptados

        if (!emailValido && correo.isNotEmpty()) {
            etCorreo.error = "Correo no válido"
        }

        if (!passwordValida && password.isNotEmpty()) {
            etPassword.error = "Debe tener al menos 8 caracteres"
        }
    }
}
