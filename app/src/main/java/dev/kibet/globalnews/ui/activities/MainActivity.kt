package dev.kibet.globalnews.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.kibet.globalnews.R
import dev.kibet.globalnews.databinding.ActivityMainBinding
import dev.kibet.globalnews.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBar.toolbar
        setSupportActionBar(toolbar)

        val drawer = binding.drawerLayout

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.headLinesFragment,
                R.id.businessFragment,
                R.id.technologyFragment,
                R.id.sportsFragment
            ),drawer
        )

        val navController = findNavController(R.id.nav_host)

        val navView = binding.navView

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}