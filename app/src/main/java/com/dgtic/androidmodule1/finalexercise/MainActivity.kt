package com.dgtic.androidmodule1.finalexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.dgtic.androidmodule1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asegurar que el fragmento se carga solo una vez
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.containerFragment, LoginFragment.newInstance())
            }
        }
    }
}
