/*
 * Developer: Vidal Ruiz
 * Date: January 31, 2025
 *
 * Description:
 * This class demonstrates the Android Activity lifecycle by implementing
 * key lifecycle methods (`onCreate`, `onStart`, `onResume`, etc.).
 * Each method logs a message or displays a Toast to indicate the current
 * state of the activity.
 *
 * Module: Android Development - Module 1
 * Topic: Activity Lifecycle & Event Handling
 */
package com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise1

import android.content.Intent
import android.os.Bundle
import com.dgtic.androidmodule1.R
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VidalLifeCycleActivity: AppCompatActivity() {


    /**
     * Called when the activity is first created.
     *
     * In the activity lifecycle, `onCreate()` is the **first method** that gets executed.
     * It is used to perform **initial setup**, including:
     * - Defining the user interface layout with `setContentView()`.
     * - Initializing variables and UI components.
     * - Setting up event listeners and other necessary elements.
     *
     * In this case, a `Toast` message is displayed with the text "onCreate",
     * indicating that the activity has entered this state.
     *
     * @param savedInstanceState Contains data from the previous state if the activity is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge layout on modern devices
        enableEdgeToEdge()

        // Sets the layout for this activity
        setContentView(R.layout.activity_vidal_life_cycle)

        // Adjusts padding to fit system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Displays a Toast message indicating that the activity has entered onCreate
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        // Set up btnReturnVidalMainPage button, which returns to VidalMainActivity.
        val btnReturnVidalMainPage = findViewById<Button>(R.id.btnReturnVidalMainPage)
        btnReturnVidalMainPage.setOnClickListener {
            val intent = Intent(this, VidalExercise1MainActivity::class.java)
            startActivity(intent)
        }
    }


    /**
     * Called when the activity becomes visible to the user.
     *
     * In the activity lifecycle, `onStart()` is executed **after `onCreate()`**
     * and before `onResume()`. It prepares the activity to become visible,
     * but the user cannot interact with it yet.
     *
     * Common use cases:
     * - Registering broadcast receivers.
     * - Initializing resources that should be available while the activity is visible.
     * - Preparing UI updates before the user can interact with the activity.
     *
     * In this case, a `Toast` message is displayed with the text "onStart",
     * indicating that the activity has entered this state.
     */
    override fun onStart() {
        super.onStart()

        // Displays a Toast message indicating that the activity has entered onStart
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }


    /**
     * Called when the activity is about to start interacting with the user.
     *
     * In the activity lifecycle, `onResume()` is executed **after `onStart()`**.
     * At this point, the activity is fully visible, and the user can interact with it.
     *
     * Common use cases:
     * - Starting animations or UI updates.
     * - Resuming a paused process (e.g., a video or a game).
     * - Acquiring resources that should be available while the activity is in the foreground.
     *
     * In this case, a `Toast` message is displayed with the text "onResume",
     * indicating that the activity has entered this state.
     */
    override fun onResume() {
        super.onResume()

        // Displays a Toast message indicating that the activity has entered onResume
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    /**
     * Called when the activity is about to go into the background.
     *
     * In the activity lifecycle, `onPause()` is executed **when the user leaves the activity**,
     * but it might still be partially visible (e.g., when opening a dialog or switching apps).
     *
     * Common use cases:
     * - Pausing animations, videos, or music playback.
     * - Saving temporary data or user progress.
     * - Releasing system resources that are not needed when the activity is not in focus.
     *
     */
    override fun onPause() {
        super.onPause()

        // Displays a Toast message indicating that the activity has entered  onPause
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    /**
     * Called when the activity is no longer visible to the user.
     *
     * In the activity lifecycle, `onStop()` is executed **when the activity moves to the background**,
     * either because the user navigated away from it or a new activity is covering it completely.
     *
     * Common use cases:
     * - Releasing resources that are not needed while the activity is in the background.
     * - Stopping animations, background processes, or UI updates.
     * - Saving persistent data before the activity is stopped.
     *
     * In this case, a `Toast` message is displayed with the text "onStop",
     * indicating that the activity has entered this state.
     */
    override fun onStop() {
        super.onStop()
        // Displays a Toast message indicating that the activity has entered onStop
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }


    /**
     * Called when the activity is about to be destroyed.
     *
     * In the activity lifecycle, `onDestroy()` is executed **when the activity is being completely removed**
     * from memory, either because the user has closed it or the system is reclaiming resources.
     *
     * Common use cases:
     * - Releasing all remaining resources (e.g., database connections, background tasks).
     * - Cleaning up memory or unregistering listeners.
     * - Performing final cleanup operations before the activity is completely shut down.
     *
     * In this case, a `Toast` message is displayed with the text "onDestroy",
     * indicating that the activity has entered this state.
     */
    override fun onDestroy() {
        super.onDestroy()

        // Displays a Toast message indicating that the activity has entered onDestroy
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    /**
     * Called when the activity is restarting after being stopped.
     *
     * In the activity lifecycle, `onRestart()` is executed **when the activity is coming back to the foreground**
     * after being stopped, but not destroyed. It is called **before `onStart()`**.
     *
     * Common use cases:
     * - Refreshing UI elements when the activity is reopened.
     * - Reinitializing resources that were released in `onStop()`.
     * - Restarting processes that were paused when the activity went to the background.
     *
     * In this case, a `Toast` message is displayed with the text "onRestart",
     * indicating that the activity has entered this state.
     */
    override fun onRestart() {
        super.onRestart()

        // Displays a Toast message indicating that the activity has entered onRestart
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }



}