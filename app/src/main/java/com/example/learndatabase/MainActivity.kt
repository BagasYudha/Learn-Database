package com.example.learndatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.learndatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Menghubungkan NavController dengan NavHostFragment
        val navController = findNavController(R.id.fragmentContainerView)

        // Setup BottomNavigationView dengan NavController
        binding.bottomBar.setupWithNavController(navController)

    }
}