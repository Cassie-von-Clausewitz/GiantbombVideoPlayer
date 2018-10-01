package com.wyrmix.giantbombvideoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.wyrmix.giantbombvideoplayer.databinding.ActivityMainBinding
import com.wyrmix.giantbombvideoplayer.extension.navigateUp
import io.palaima.debugdrawer.DebugDrawer
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

/**
 * garbage activity to launch into different parts of the app during development
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.main_nav_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)

        setupActionBarWithNavController(navController)

        // Set up bottom_navigation menu
//        binding.mainNavFragment.setupWithNavController(navController)

        get<DebugDrawer> { parametersOf(this) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.main_nav_fragment))
//        return navigateUp(null, findNavController(R.id.main_nav_fragment))
    }
}
