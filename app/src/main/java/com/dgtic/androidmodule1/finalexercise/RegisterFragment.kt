package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dgtic.androidmodule1.R

/**
 * Fragmento que representa la pantalla de registro de usuarios.
 * Permite al usuario ingresar sus datos, seleccionar su género y tipo de acceso,
 * aceptar los términos y registrarse en la aplicación.
 */
class RegisterFragment : Fragment() {

    // Declaración de variables para los componentes de la UI
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

    /**
     * Inflar el layout del fragmento.
     * @return Vista inflada correspondiente a `fragment_register.xml`.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    /**
     * Método llamado después de que la vista ha sido creada.
     * Se encarga de inicializar los componentes y establecer los listeners.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar componentes de la UI
        initComponents(view)

        // Cargar opciones en el Spinner
        loadData(view)

        // Configurar eventos y validaciones
        setListeners()

        // Desactivar el botón de registro por defecto
        btnRegister.isEnabled = false

        // Por defecto, la opción de "Hombre" está seleccionada
        rbMale.isChecked = true
    }

    /**
     * Inicializa los componentes de la UI con `findViewById`.
     * Usa un `try/catch` para evitar errores si algún componente no está presente en la vista.
     * @param view Vista raíz del fragmento.
     */
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

        } catch (e: Exception) {
            // Captura y muestra un mensaje si algún elemento no se encuentra en la vista
            Log.e("RegisterFragment", "Error al inicializar componentes: ${e.message}")
            Toast.makeText(view.context, "Error al cargar los elementos de la UI", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Configura los listeners de los componentes de la UI.
     * - Habilita o deshabilita el botón de registro según la aceptación de términos.
     * - Maneja el evento del botón de registro para validar los datos antes de proceder.
     */
    private fun setListeners() {
        // Habilita el botón de registro solo si los términos han sido aceptados
        chkTerms.setOnCheckedChangeListener { _, isChecked ->
            btnRegister.isEnabled = isChecked
        }

        // Listener del botón de registro
        btnRegister.setOnClickListener {
            val isValid = validarFormulario()

            if (isValid) {
                // Navegar al LoginFragment después del registro exitoso
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, LoginFragment.newInstance())
                    .addToBackStack("SecondFragment")
                    .commit()
            }
        }
    }

    /**
     * Carga los datos en el Spinner de tipo de acceso.
     * @param view Vista raíz del fragmento.
     */
    private fun loadData(view: View) {
        val opciones = arrayOf("Select an level of access", "Free Access", "Limited announcements", "Free of publicity")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, opciones)
        spAccessType.adapter = adapter
    }

    /**
     * Valida los datos ingresados en el formulario antes de proceder con el registro.
     * @return `true` si la validación es exitosa, `false` en caso contrario.
     */
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

        // Validar que se seleccione un tipo de acceso válido
        if (tipoAcceso == "Select an level of access") {
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

    /**
     * Método de fábrica para instanciar el fragmento.
     * @return Nueva instancia de `RegisterFragment`.
     */
    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
