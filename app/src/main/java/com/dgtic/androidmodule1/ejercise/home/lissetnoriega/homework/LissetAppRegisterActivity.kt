package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.homework

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
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
    private lateinit var spCountry: Spinner
    private lateinit var cbConditions: CheckBox
    private lateinit var btnRegister: Button

    private fun initViews(){
        etName = findViewById(R.id.etName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword1 = findViewById(R.id.etPassword1)
        etPassword2 = findViewById(R.id.etPassword2)
        spCountry = findViewById(R.id.spCountry)
        cbConditions = findViewById(R.id.cbConditions)
        btnRegister = findViewById(R.id.btnRegister)
    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateFields(){
        val name = etName.text.toString()
        val lastName = etLastName.text.toString()
        val email = etEmail.text.toString()
        val password1 = etPassword1.text.toString()
        val password2 = etPassword2.text.toString()
        val termsAccepted = cbConditions.isChecked

        val isEmailValid = isValidEmail(email)
        val doPasswordsMatch = password1.isNotEmpty() && (password1 == password2)
        val fullNameOk = name.isNotEmpty() && lastName.isNotEmpty()
        val isFormValid = termsAccepted && isEmailValid && doPasswordsMatch && fullNameOk

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
        spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemSelected = data[position]

                if (position == 0) {
                    Toast.makeText(
                        this@LissetAppRegisterActivity,
                        "Item no seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LissetAppRegisterActivity,
                        "Item seleccionado: $itemSelected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        validateFields()
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        ibHome.setOnClickListener {
            val homeIntent = Intent(this, LissetMainActivity::class.java)
            startActivity(homeIntent)
        }
    }
}