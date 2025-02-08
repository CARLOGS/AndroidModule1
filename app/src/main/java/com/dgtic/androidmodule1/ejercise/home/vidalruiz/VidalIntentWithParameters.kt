/*
 * Developer: Vidal Ruiz
 * Date: February 3, 2025
 *
 * Description:
 * This Activity receives parameters from an Intent and displays them on the screen.
 * It extracts data such as name, age, and marital status from the Intent extras
 * and updates a TextView accordingly. Additionally, it returns a result parameter
 * indicating whether the received data was processed successfully.
 *
 * Module: Android Development - Module 1
 * Topic: Passing and Returning Parameters with Intents
 */

package com.dgtic.androidmodule1.ejercise.home.vidalruiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R

class VidalIntentWithParameters : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     *
     * This method initializes the UI components, applies edge-to-edge display settings,
     * retrieves parameters from the Intent extras, and sets up event listeners for the buttons.
     * Additionally, it prepares an Intent result to send data back to the calling Activity.
     *
     * @param savedInstanceState Contains data from the previous state if the activity is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge UI display.
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_intent_with_parameters)

        // Adjusts padding for system bars (status bar, navigation bar, etc.).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the TextView to display received parameters.
        val tvParameters = findViewById<TextView>(R.id.tvParameters)
        tvParameters.text = ""

        // Variables to store received parameters.
        var name: String? = null
        var age: Int? = null
        var married: Boolean? = null

        // Retrieve parameters from Intent extras.
        intent.extras?.let {
            if (it.containsKey("EXTRA_NAME")) {
                name = it.getString("EXTRA_NAME")
            }
            if (it.containsKey("EXTRA_AGE")) {
                age = it.getInt("EXTRA_AGE")
            }
            if (it.containsKey("EXTRA_MARRIED")) {
                married = it.getBoolean("EXTRA_MARRIED")
            }

            // Display extracted parameters.
            tvParameters.text = "Name: ${name.orEmpty()} Age: ${age?.toString() ?: "N/A"} Married: ${married ?: "N/A"}"
        }

        // Initialize button to return result to the calling Activity.
        val btnReturnVidalMainPage = findViewById<Button>(R.id.btnReturnVidalMainPage)
        btnReturnVidalMainPage.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("Extra_IS_CORRECT", true)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
