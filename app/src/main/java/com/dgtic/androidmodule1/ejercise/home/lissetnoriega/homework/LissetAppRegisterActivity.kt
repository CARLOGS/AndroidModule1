package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.homework

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.lissetnoriega.LissetMainActivity

class LissetAppRegisterActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword1: EditText
    private lateinit var etPassword2: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spCountry: Spinner
    private lateinit var cbConditions: CheckBox
    private lateinit var btnRegister: Button
    private lateinit var tvGender: TextView
    private lateinit var tvCountry: TextView

    private fun initViews(){
        etName = findViewById(R.id.etName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword1 = findViewById(R.id.etPassword1)
        etPassword2 = findViewById(R.id.etPassword2)
        rgGender = findViewById(R.id.rgGender)
        spCountry = findViewById(R.id.spCountry)
        cbConditions = findViewById(R.id.cbConditions)
        btnRegister = findViewById(R.id.btnRegister)
        tvGender = findViewById(R.id.tvGender)
        tvCountry = findViewById(R.id.tvCountry)
    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateFields(){
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val password1 = etPassword1.text.toString()
        val password2 = etPassword2.text.toString()

        var isFormValid = true

        if(name.isEmpty()){
            etName.error = "El nombre es obligatorio"
            isFormValid = false
        }
        if(!isValidEmail(email)){
            etEmail.error = "Ingresa un correo válido"
            isFormValid = false
        }
        if(password1.length < 6){
            etPassword1.error = "La contraseña debe tener al menos 6 caracteres"
            isFormValid = false
        }
        if(password1 != password2){
            etPassword2.error = "Las contraseñas no coinciden"
            isFormValid = false
        }
        if(rgGender.checkedRadioButtonId == -1){
            tvGender.error = "Selecciona un género"
            isFormValid = false
        }
        if(spCountry.selectedItemPosition == 0){
            tvCountry.error = "Selecciona un país"
            isFormValid = false
        }
        if(!cbConditions.isChecked){
            isFormValid = false
        }

        btnRegister.isEnabled = isFormValid
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ibBack = findViewById<ImageButton>(R.id.ibBack)
        val ibHome = findViewById<ImageButton>(R.id.ibHome)
        initViews()
        btnRegister.isEnabled = false

        //Spinner
        val data = arrayListOf("Selecciona un país", "México", "Argentina", "Colombia", "Ecuador", "Dinamarca", "España")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spCountry.adapter = adapter

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { validateFields() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        etName.addTextChangedListener(textWatcher)
        etLastName.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etPassword1.addTextChangedListener(textWatcher)
        etPassword2.addTextChangedListener(textWatcher)
        cbConditions.setOnCheckedChangeListener { _, _ -> validateFields() }
        spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                validateFields()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword1.text.toString()
            val genderId = rgGender.checkedRadioButtonId
            val gender = if (genderId == R.id.rbMan) "Masculino" else "Femenino"
            val country = spCountry.selectedItem.toString()

            val secondIntent = Intent(this, LissetSecondRegisterActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_LAST_NAME", lastName)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_PASSWORD", password)
                putExtra("EXTRA_GENDER", gender)
                putExtra("EXTRA_COUNTRY", country)
            }
            startActivity(secondIntent)
        }

        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}