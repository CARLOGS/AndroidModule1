package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.dgtic.androidmodule1.R

class RegisterFragment : Fragment() {

    private lateinit var tvTitle: TextView
    private lateinit var etEmail: EditText
    private lateinit var etName: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvGender: TextView
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var spAccessType: Spinner
    private lateinit var chkTerms: CheckBox
    private lateinit var btnRegister: Button
    private lateinit var tvPasswordStrength: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        loadData(view)
        setListeners()
        btnRegister.isEnabled = false
        rbMale.isChecked = true
        tvPasswordStrength.visibility = View.GONE

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initComponents(view: View) {
        try {
            tvTitle = view.findViewById(R.id.tvTitle)
            etEmail = view.findViewById(R.id.etEmail)
            etName = view.findViewById(R.id.etName)
            etPassword = view.findViewById(R.id.etPassword)
            etConfirmPassword = view.findViewById(R.id.etConfirmPassword)
            tvGender = view.findViewById(R.id.tvGender)
            rgGender = view.findViewById(R.id.rgGender)
            rbMale = view.findViewById(R.id.rbMale)
            rbFemale = view.findViewById(R.id.rbFemale)
            spAccessType = view.findViewById(R.id.spAccessType)
            chkTerms = view.findViewById(R.id.chkTerms)
            btnRegister = view.findViewById(R.id.btnRegister)
            tvPasswordStrength = view.findViewById(R.id.tvPasswordStrength)

            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            etConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        } catch (e: Exception) {
            Log.e("RegisterFragment", "Error al inicializar componentes: ${e.message}")
            Toast.makeText(view.context, "Error al cargar los elementos de la UI", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        chkTerms.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    tvPasswordStrength.visibility = View.GONE
                } else {
                    tvPasswordStrength.visibility = View.VISIBLE
                    val strength = getPasswordStrength(s.toString())
                    tvPasswordStrength.text = "Password Strength: $strength"

                    when (strength) {
                        "Weak" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
                        "Medium" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark))
                        "Strong" -> tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnRegister.setOnClickListener {
            if (validarFormulario()) {
                val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val existingEmail = sharedPreferences.getString("email", "")
                val newEmail = etEmail.text.toString().trim()

                if (existingEmail == newEmail) {
                    Toast.makeText(requireContext(), "User already exists!", Toast.LENGTH_SHORT).show()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.putString("email", newEmail)
                    editor.putString("password", etPassword.text.toString().trim())
                    editor.apply()

                    Toast.makeText(requireContext(), "User registered successfully!", Toast.LENGTH_SHORT).show()

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.containerFragment, LoginFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    private fun loadData(view: View) {
        val opciones = arrayOf("Select an level of access", "Free Access", "Limited announcements", "Free of publicity")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, opciones)
        spAccessType.adapter = adapter
    }

    private fun validarFormulario(): Boolean {
        val email = etEmail.text.toString().trim()
        val nombre = etName.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val tipoAcceso = spAccessType.selectedItem.toString()

        if (email.isEmpty() || nombre.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_SHORT).show()
            return false
        }

        if (tipoAcceso == "Select an level of access") {
            Toast.makeText(requireContext(), "Please select a valid user type", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun getPasswordStrength(password: String): String {
        return when {
            password.length >= 10 && password.matches(".*[A-Z].*".toRegex()) && password.matches(".*\\d.*".toRegex()) -> "Strong"
            password.length >= 6 -> "Medium"
            else -> "Weak"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
