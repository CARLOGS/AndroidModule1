/*
 * Developer: Vidal Ruiz
 * Date: February 3, 2025
 *
 * Description:
 * This is the main Activity that serves as the home screen for navigating to other exercises.
 * It provides buttons to launch secondary activities demonstrating different Android concepts,
 * such as the activity lifecycle, intent handling, and passing parameters between activities.
 *
 * Additionally, it registers for an activity result and processes a boolean parameter returned
 * from a secondary activity.
 *
 * Module: Android Development - Module 1
 * Topic: Activity Navigation & Intent Handling
 */

package com.dgtic.androidmodule1.ejercise.home.vidalruiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.TeamActivity

class VidalMainActivity : AppCompatActivity() {

    /**
     * Registers for an activity result from a secondary activity.
     *
     * This listener handles the response from `VidalIntentWithParameters`, checking whether
     * the returned result indicates a successful operation (`EXTRA_IS_CORRECT`).
     */
    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val isCorrect = result.data?.getBooleanExtra("EXTRA_IS_CORRECT", false)
            Toast.makeText(this, "Parameter received: $isCorrect", Toast.LENGTH_SHORT).show()
        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "No parameter received, operation canceled", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Called when the activity is first created.
     *
     * This method initializes UI components, enables edge-to-edge display settings,
     * and sets up event listeners for navigation buttons.
     *
     * @param savedInstanceState Contains data from the previous state if the activity is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_main)

        // Adjusts padding for system bars (status bar, navigation bar, etc.).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Button to launch VidalLifeCycleActivity (Exercise 1)
        val btnExercise1 = findViewById<Button>(R.id.btnExercise1)
        btnExercise1.setOnClickListener {
            val intent = Intent(this, VidalLifeCycleActivity::class.java)
            startActivity(intent)
        }

        // Button to launch VidalIntentActivity (Exercise 2)
        val btnExercise2 = findViewById<Button>(R.id.btnExercise2)
        btnExercise2.setOnClickListener {
            val intent = Intent(this, VidalIntentActivity::class.java)
            startActivity(intent)
        }

        // Button to launch VidalIntentWithParameters (Exercise 3) with predefined parameters
        val btnExercise3 = findViewById<Button>(R.id.btnExercise3)
        btnExercise3.setOnClickListener {
            val intent = Intent(this, VidalIntentWithParameters::class.java)
            intent.putExtra("EXTRA_NAME", "Vidal")
            intent.putExtra("EXTRA_MARRIED", true)
            intent.putExtra("EXTRA_AGE", 49)
            register.launch(intent)
        }

        // Button to return to MainActivity (Home)
        val btnReturnMainPage = findViewById<Button>(R.id.btnReturnMainPage)
        btnReturnMainPage.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }

    }
}
