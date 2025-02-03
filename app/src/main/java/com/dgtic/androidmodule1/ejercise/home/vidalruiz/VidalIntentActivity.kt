/*
 * Developer: Vidal Ruiz
 * Date: February 3, 2025
 *
 * Description:
 * This Activity contains a button that, when clicked, sends an implicit Intent 
 * to open a URL in a web browser. It also provides a button to return to the main Activity.
 *
 * Module: Android Development - Module 1
 * Topic: Implicit Intents
 */

package com.dgtic.androidmodule1.ejercise.home.vidalruiz

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class VidalIntentActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     *
     * This method initializes the UI components, applies edge-to-edge display settings,
     * and sets up event listeners for the buttons.
     *
     * @param savedInstanceState Contains data from the previous state if the activity is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge UI display.
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_intent)

        // Adjusts padding for system bars (status bar, navigation bar, etc.).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(R.layout.activity_vidal_intent)

        // Initialize button to open a URL in a web browser.
        val btnVidalOpenURL = findViewById<Button>(R.id.btnVidalOpenURL)
        btnVidalOpenURL.setOnClickListener {

            // Create an implicit Intent to open a URL.
            val openURLIntentActivity = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("http://www.google.com")
            }

            // Start the activity using a chooser to select a web browser.
            startActivity(Intent.createChooser(openURLIntentActivity, "Open URL using"))
        }

        // Initialize button to return to VidalMainActivity.
        val btnReturnVidalMainPage = findViewById<Button>(R.id.btnReturnVidalMainPage)
        btnReturnVidalMainPage.setOnClickListener {
            val intent = Intent(this, VidalMainActivity::class.java)
            startActivity(intent)
        }
    }
}
