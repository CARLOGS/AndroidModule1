package com.dgtic.androidmodule1.finalexercise

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    // Recupera el Binding de Fragment
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inicializa el Binding
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickableRegisterText(binding.tvRegister, this)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        // Muestra el ActionBar
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun loginUser() {
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")

        val inputEmail = binding.etEmail.text.toString().trim()
        val inputPassword = binding.etPassword.text.toString().trim()

        if (inputEmail == savedEmail && inputPassword == savedPassword && !inputEmail.isEmpty()) {
            Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()

            // Guardar la fecha y hora del último inicio de sesión
            val editor = sharedPreferences.edit()
            editor.putString("lastLogin", System.currentTimeMillis().toString())
            editor.apply()

            // Navegar a LoggedUserActivity sin cerrar MainActivity
            val intent = Intent(requireActivity(), LoggedUserActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP  // Evita duplicar actividades
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(),
                getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    fun setClickableRegisterText(textView: TextView, fragment: LoginFragment) {
        val fullText = "If you do not have an username, please register: here"
        val spannableString = android.text.SpannableString(fullText)

        val clickableSpan = object : android.text.style.ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, RegisterFragment.newInstance())
                    .addToBackStack("SecondFragment")
                    .commit()
            }
        }

        val colorSpan = android.text.style.ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(), android.R.color.holo_blue_dark))

        val startIndex = fullText.indexOf("here")
        val endIndex = startIndex + "here".length

        spannableString.setSpan(clickableSpan, startIndex, endIndex, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, startIndex, endIndex, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
        textView.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    }
}
