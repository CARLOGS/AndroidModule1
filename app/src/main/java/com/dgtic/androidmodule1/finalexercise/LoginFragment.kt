package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dgtic.androidmodule1.R

/**
 * Fragmento que representa la pantalla de inicio de sesión.
 * Permite al usuario ingresar sus credenciales y navegar al registro si no tiene cuenta.
 */
class LoginFragment : Fragment() {

    /**
     * Infla el layout del fragmento de inicio de sesión.
     * @return Vista inflada correspondiente a `fragment_login.xml`.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    /**
     * Método llamado después de que la vista ha sido creada.
     * Se encarga de inicializar componentes y establecer eventos de clic.
     * @param view Vista raíz del fragmento.
     * @param savedInstanceState Estado previo guardado de la instancia.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvRegister = view.findViewById<TextView>(R.id.tvRegister)

        setClickableRegisterText(tvRegister, this)
    }
    /**
     * Método de fábrica para instanciar el fragmento.
     * @return Nueva instancia de `LoginFragment`.
     */
    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
    /**
     * Aplica formato de texto interactivo al mensaje de registro.
     * Permite que la palabra "here" sea un enlace clickeable que navega al `RegisterFragment`.
     *
     * @param textView `TextView` donde se aplicará el formato.
     */
    fun setClickableRegisterText(textView: TextView, fragment: LoginFragment) {
        val fullText = "If you do not have an username, please register: here"
        val spannableString = SpannableString(fullText)

        // Crear un ClickableSpan que maneje la navegación al fragmento de registro
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, RegisterFragment.newInstance())
                    .addToBackStack("SecondFragment")
                    .commit()
            }
        }

        // Aplicar color azul a la palabra "here"
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(), android.R.color.holo_blue_dark))

        // Encontrar la posición de la palabra "here" en el texto
        val startIndex = fullText.indexOf("here")
        val endIndex = startIndex + "here".length

        // Aplicar efectos al texto dentro de los índices correspondientes
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Asignar el texto modificado al TextView
        textView.text = spannableString
        textView.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    }
}