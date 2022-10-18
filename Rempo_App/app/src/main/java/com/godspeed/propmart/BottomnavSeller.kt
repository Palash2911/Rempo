package com.godspeed.propmart

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.godspeed.propmart.databinding.ActivityBottomnavSellerBinding

class BottomnavSeller : AppCompatActivity() {

    private lateinit var binding: ActivityBottomnavSellerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomnavSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        binding.navView.menu.findItem(R.id.navigation_home).isVisible = true
        binding.navView.menu.findItem(R.id.navigation_Hompage).isVisible = false
        binding.navView.menu.findItem(R.id.navigation_Bookmarks).isVisible = false;
        binding.navView.menu.findItem(R.id.navigation_Location).isVisible = false;
        val navController = findNavController(R.id.nav_host_fragment_activity_bottomnav_seller)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_more
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}