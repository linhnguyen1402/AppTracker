package com.ndta.apptracker.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ndta.apptracker.R
import com.ndta.apptracker.databinding.ActivityMainBinding
import com.ndta.apptracker.db.RunDAO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var runDAO: RunDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            setSupportActionBar(toolbar)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            bottomNavigationView.setupWithNavController(navHostFragment.navController)
            navHostFragment.navController
                .addOnDestinationChangedListener { controller, destination, arguments ->
                    when (destination.id) {
                        R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment -> bottomNavigationView.visibility =
                            View.VISIBLE
                        else -> bottomNavigationView.visibility = View.GONE
                    }
                }
        }

    }
}