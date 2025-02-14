package com.dgtic.androidmodule1.ejercise.home.carlogarcia.homework

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

/**
 * Activity de control de acceso y registro
 *
 * @author Carlo García Sánchez
 */
class CgsRegisterActivity : AppCompatActivity() {
    // Definición de constantes
    companion object {
        const val LOWER_LETTERS = "abcdefghijklmnñopqrstuvwxyz"
        const val UPPER_LETTERS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        const val NUMBERS = "123456789"
        const val SYMBOLS = "!$%&.#-_"
    }

    // Definición de controles
    private lateinit var selectImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var txtUser: EditText
    private lateinit var txtPass: EditText
    private lateinit var txtRPass: EditText
    private lateinit var txtName: EditText
    private lateinit var txtAge: EditText
    private lateinit var txtMail: EditText
    private lateinit var grpGender: RadioGroup
    private lateinit var btnSave: Button

    private var suri: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_cgs_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recupera controles
        val btnGoHome = findViewById<ImageView>(R.id.btnGoHome)
        val btnBackward = findViewById<ImageView>(R.id.btnBackward)
        val imgPhoto = findViewById<ImageView>(R.id.imgPhoto)
        val imgEdit = findViewById<ImageView>(R.id.imgEdit)
        var gender: String = "Hombre"

        // Inicia los controles
        initControls()

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

        //RADIOGROUP
        grpGender.setOnCheckedChangeListener { group, checkedId ->
            when ( checkedId ) {
                R.id.radMale -> gender = "Hombre"
                R.id.radFemale -> gender = "Mujer"
            }

        }

        btnSave.setOnClickListener {
            if (validations()) {
                val resultIntent = Intent().apply {
                    putExtra("EXTRA_USER", txtUser.text.toString())
                    putExtra("EXTRA_PASS", txtPass.text.toString())
                    putExtra("EXTRA_NAME", txtName.text.toString())
                    putExtra("EXTRA_GENDER", gender)
                    putExtra("EXTRA_AGE", txtAge.text.toString())
                    putExtra("EXTRA_MAIL", txtMail.text.toString())
                    putExtra("EXTRA_IMAGE_URI", suri)
                }

                setResult(RESULT_OK, resultIntent)

                // Destruye/cierra este Activity
                finish()
            }
        }

        // Acción OnClick para buscar una foto
        imgPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageLauncher.launch(Intent.createChooser(intent, "Abrir usando:"))
        }
        imgEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageLauncher.launch(Intent.createChooser(intent, "Abrir usando:"))
        }

        // Inicializar el lanzador para seleccionar imágenes
        selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    // Guarda el URI
                    suri = uri.toString()
                    // Asigna el URI
                    imgPhoto.setImageURI(uri) // Muestra la imagen en el ImageView
                }
            }
        }
    }

    /**
     * Valida el formulario
     *
     * Función con todos los criterio de validación de datos
     */
    private fun validations(): Boolean {
        var valid = true

        // Valida datos obligatorios
        if (txtUser.text.isEmpty()) {
            valid = false
            Toast.makeText(this, "El usuario es obligatorio", Toast.LENGTH_SHORT).show()
        } else if (txtPass.text.isEmpty()) {
            valid = false
            Toast.makeText(this, "La clave es obligatoria", Toast.LENGTH_SHORT).show()
        } else if (txtName.text.isEmpty()) {
            valid = false
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
        } else if (txtMail.text.isEmpty()) {
            valid = false
            Toast.makeText(this, "El correo es obligatorio", Toast.LENGTH_SHORT).show()
        // Valida longitud de la clave
        } else if (txtPass.text.toString().length < 8) {
            valid = false
            Toast.makeText(this, "La clave debe medir al menos 8 caracteres", Toast.LENGTH_SHORT)
                .show()
        // Valida clave válida
        } else if (!isValidPass(txtPass.text.toString())) {
            valid = false
            Toast.makeText(
                this,
                "La clave debe contener mayúsculas, minúsculas, números y un caracter $SYMBOLS",
                Toast.LENGTH_LONG
            ).show()
            // Valida clave correcta
        } else if (!txtPass.text.toString().equals(txtRPass.text.toString())) {
            valid = false
            Toast.makeText(this, "La clave no coincide", Toast.LENGTH_SHORT).show()
            // Valida correo correcto
        } else if (
            !txtMail.text.toString().contains("@")
            || !txtMail.text.toString().contains(".")
            || txtMail.text.toString().length < 6
            || !isValidEmail(txtMail.text.toString())
        ) {
            valid = false
            Toast.makeText(this, "Correo incorrecto", Toast.LENGTH_SHORT).show()
        }

        return valid
    }

    private fun initControls() {
        txtUser = findViewById<EditText>(R.id.txtUser)
        txtPass = findViewById<EditText>(R.id.txtPass)
        txtRPass = findViewById<EditText>(R.id.txtRPass)
        txtName = findViewById<EditText>(R.id.txtName)
        txtAge = findViewById<EditText>(R.id.txtAge)
        txtMail = findViewById<EditText>(R.id.txtMail)
        grpGender = findViewById<RadioGroup>(R.id.grpGender)
        btnSave = findViewById<Button>(R.id.btnSave)

        // Selecciona un género
        grpGender.check(R.id.radMale)

        // Cambia colores
        btnSave.setBackgroundColor(Color.argb(255, 0,64,121))
        btnSave.setTextColor(Color.WHITE)
    }

    /**
     * Valida correo
     *
     * Función para validar una cuenta de correo bien formada
     * basado en un patron
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Valida contraseña
     *
     * Función para validar si la clave cumple
     * con los criterio de al menos una mayúscula,
     * una minúscula, un número y un caracter especial
     */
    private fun isValidPass(pass : String): Boolean {
        var valid : Boolean = true
        var contain : Boolean = false

        // Valida minúsculas
        for (letter in pass) {
            if (LOWER_LETTERS.contains(letter)) {
                contain = true
                break
            }
        }
        valid = valid && contain

        contain = false
        // Valida mayúsculas
        for (letter in pass) {
            if (UPPER_LETTERS.contains(letter)) {
                contain = true
                break
            }
        }
        valid = valid && contain

        contain = false
        // Valida números
        for (letter in pass) {
            if (NUMBERS.contains(letter)) {
                contain = true
                break
            }
        }
        valid = valid && contain

        contain = false
        // Valida símbolos
        for (letter in pass) {
            if (SYMBOLS.contains(letter)) {
                contain = true
                break
            }
        }
        valid = valid && contain

        return valid
    }
}