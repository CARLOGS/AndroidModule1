package com.dgtic.androidmodule1.ejercise.home.vidalruiz.homework

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class VidalAccountCreationActivity : AppCompatActivity() {

    // Declaración de todos los elementos de la UI
    private lateinit var imgUser: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var etEmail: EditText
    private lateinit var etNombre: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvGender: TextView
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var spnTipoAcceso: Spinner
    private lateinit var chkTerms: CheckBox
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vidal_account_creation)

        // Inicializar los componentes
        initComponents()
        setListeners()
        loadData()

        // Desactivar el botón al inicio
        btnRegister.isEnabled = false
        //Por default hombre esta activo.
        rbMale.isChecked = true

    }

    private fun initComponents() {
        imgUser = findViewById(R.id.imgUser)
        tvTitle = findViewById(R.id.tvTitle)
        etEmail = findViewById(R.id.etEmail)
        etNombre = findViewById(R.id.etNombre)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvGender = findViewById(R.id.tvGender)
        rgGender = findViewById(R.id.rgGender)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        spnTipoAcceso = findViewById(R.id.spnTipoAcceso)
        chkTerms = findViewById(R.id.chkTerms)
        btnRegister = findViewById(R.id.btnRegistro)
    }

    private fun setListeners() {
        // Habilitar o deshabilitar el botón de registro según el estado del checkbox
        chkTerms.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        // Validar contraseñas cuando se presiona el botón de registro
        btnRegister.setOnClickListener {
            validarFormulario()
        }
    }

    private fun loadData() {
        // Configurar el Spinner con opciones predefinidas
        val opciones = arrayOf("Select an option", "Gratuito", "Usuario sin anuncios", "Super Usuario")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spnTipoAcceso.adapter = adapter
    }
    private fun validarFormulario(): Boolean {
        val email = etEmail.text.toString().trim()
        val nombre = etNombre.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val tipoAcceso = spnTipoAcceso.selectedItem.toString()

        // Validar que todos los campos estén llenos
        if (email.isEmpty() || nombre.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que se seleccione un tipo de acceso válido (evitar "Select an option")
        if (tipoAcceso == "Select an option") {
            Toast.makeText(this, "Please select a valid user type", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que las contraseñas coincidan
        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


}
