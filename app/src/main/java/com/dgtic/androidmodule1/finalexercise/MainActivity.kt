package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Recupera el Binding de Fragment
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        // Asegurar que el fragmento se carga solo una vez
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.containerFragment, LoginFragment.newInstance())
            }
        }
    }
}
