package com.tugas.www.finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_main)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_calendar,
            R.id.nav_chart,
            R.id.nav_home,
            R.id.nav_history,
            R.id.nav_settings
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)

    }
}