package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.dgtic.androidmodule1.R


class RegisterFragment : Fragment() {
    // Declaración de variables de UI
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        setListeners()
        loadData(view);

        /* val btSecondFragment = view.findViewById<Button>(R.id.btSecondFragment)
        btSecondFragment.setOnClickListener {

        } */
    }

    /**
     * Inicializa los componentes de la UI.
     * Se usa try/catch para manejar posibles errores si un ID no existe en la vista.
     */
    private fun initComponents(view: View) {
        try {
            // Asignación de vistas mediante findViewById
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

        } catch (e: Exception) {
            // Captura y muestra un mensaje si algún elemento no se encuentra en la vista
            Log.e("InitComponents", "Error al inicializar componentes: ${e.message}")
            Toast.makeText(view.context, "Error al cargar los elementos de la UI", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        // Habilitar o deshabilitar el botón de registro según el estado del checkbox
        chkTerms.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        // Validar contraseñas cuando se presiona el botón de registro
        btnRegister.setOnClickListener {
            val isValid = validarFormulario()
             if (isValid) {
                 parentFragmentManager.beginTransaction()
                     .replace(R.id.containerFragment, LoginFragment.newInstance())
                     .addToBackStack("SecondFragment")
                     .commit()
             }
        }
    }

    private fun loadData(view: View) {
        val spAccessType = view.findViewById<Spinner>(R.id.spAccessType)
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

        // Validar que todos los campos estén llenos
        if (email.isEmpty() || nombre.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que se seleccione un tipo de acceso válido (evitar "Select an option")
        if (tipoAcceso == "Select an option") {
            Toast.makeText(requireContext(), "Please select a valid user type", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que las contraseñas coincidan
        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}