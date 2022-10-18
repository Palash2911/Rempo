package com.godspeed.propmart

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.godspeed.propmart.databinding.ActivityBottomtabBinding

class Bottomtab : AppCompatActivity() {

    private lateinit var binding: ActivityBottomtabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomtabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        binding.navView.menu.findItem(R.id.navigation_home).isVisible = false
        val navController = findNavController(R.id.nav_host_fragment_activity_bottomtab)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_Location, R.id.navigation_Hompage, R.id.navigation_Bookmarks, R.id.navigation_more
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}