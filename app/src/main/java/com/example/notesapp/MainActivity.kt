package com.example.notesapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected

class MainActivity : AppCompatActivity() {
    /**
     * Main Activity
     *
     * Serves as placeholder for navHost
     *
     * @author Timothy Chan
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /*
       Navigate to a destination when an item is clicked.
    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        when (item.itemId) {
            R.id.add -> {
                // Handle Item 1 click
                val action = TasksFragmentDirections.actionTasksFragmentToNoteFragment()
                navController.navigate(action)
                return true
            }
            R.id.login -> {
                // Handle Item 2 click

                return true
            }
            // Add more cases for additional menu items
            else -> return super.onOptionsItemSelected(item)
        }
    }
}