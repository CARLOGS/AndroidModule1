package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
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

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvRegister = view.findViewById<TextView>(R.id.tvRegister)

        setClickableRegisterText(tvRegister, this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    fun setClickableRegisterText(textView: TextView, fragment: LoginFragment) {
        val fullText = "If you do not have an username, please register: here"
        val spannableString = SpannableString(fullText)

        // Crear el span clickeable
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, RegisterFragment.newInstance())
                    .addToBackStack("SecondFragment")
                    .commit()
            }
        }

        // Aplicar color azul
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(), android.R.color.holo_blue_dark))

        val startIndex = fullText.indexOf("here")
        val endIndex = startIndex + "here".length

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
        textView.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    }
}