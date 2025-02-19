package com.dgtic.androidmodule1.finalexercise

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    // Recupera el Binding de Fragment
    private lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inicializa el Binding
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

//        return inflater.inflate(R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents(view)
        loadData(view)
        setListeners()

        // Para que responda el onOptionIntemSelected
        setHasOptionsMenu(true)

        binding.btnRegister.isEnabled = false
        binding.rbMale.isChecked = true
        binding.tvPasswordStrength.visibility = View.GONE

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initComponents(view: View) {
        try {
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        } catch (e: Exception) {
            Log.e("RegisterFragment", "Error al inicializar componentes: ${e.message}")
            Toast.makeText(view.context, "Error al cargar los elementos de la UI", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        binding.chkTerms.setOnCheckedChangeListener { _, isChecked ->
            binding.btnRegister.isEnabled = isChecked
        }

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.tvPasswordStrength.visibility = View.GONE
                } else {
                    binding.tvPasswordStrength.visibility = View.VISIBLE
                    val strength = getPasswordStrength(s.toString())
                    binding.tvPasswordStrength.text = "Password Strength: $strength"

                    when (strength) {
                        "Weak" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
                        "Medium" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark))
                        "Strong" -> binding.tvPasswordStrength.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnRegister.setOnClickListener {
            if (validarFormulario()) {
                val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val existingEmail = sharedPreferences.getString("email", "")
                val newEmail = binding.etEmail.text.toString().trim()

                if (existingEmail == newEmail) {
                    Toast.makeText(requireContext(), "User already exists!", Toast.LENGTH_SHORT).show()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.putString("email", newEmail)
                    editor.putString("password", binding.etPassword.text.toString().trim())
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
        binding.spAccessType.adapter = adapter
    }

    private fun validarFormulario(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val nombre = binding.etName.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        val tipoAcceso = binding.spAccessType.selectedItem.toString()

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

    // Sobre carga el método para regresar en el icono home desde un Fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
