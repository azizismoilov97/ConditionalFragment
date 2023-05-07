package com.example.conditionalfragment

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.conditionalfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val userViewModel : UserViewModel by viewModels()

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController(R.id.nav_host_fragment_activity_main)}

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}