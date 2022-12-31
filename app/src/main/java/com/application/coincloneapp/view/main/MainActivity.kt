package com.application.coincloneapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.application.coincloneapp.R
import com.application.coincloneapp.databinding.ActivityMainBinding
import com.application.coincloneapp.databinding.ActivitySelectBinding
import com.application.coincloneapp.view.setting.SettingActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setting.setOnClickListener {
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)


    }
}