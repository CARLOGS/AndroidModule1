/*
Developer:  Vidal Ruiz
Date: Januray 31, 2025.
Description:  First class of module 1.  Events
 */
package com.dgtic.androidmodule1.ejercise.home.vidalruiz

import android.os.Bundle
import com.dgtic.androidmodule1.R
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LifeCycleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.e(/* tag = */ "LifeCycleActivity", /* msg = */ "onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

    }

    //Supposed to be here
    override fun onStart() {
        super.onStart()
        Log.e("LifeCycleActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("LifeCycleActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LifeCycleActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("LifeCycleActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LifeCycleActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("LifeCycleActivity", "onRestart")
    }


}