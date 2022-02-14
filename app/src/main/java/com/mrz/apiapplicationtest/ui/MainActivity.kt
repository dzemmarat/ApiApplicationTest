package com.mrz.apiapplicationtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.ActivityMainBinding
import com.mrz.apiapplicationtest.extensions.gone
import com.mrz.apiapplicationtest.extensions.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация биндинга для активити
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация тулбара
        setSupportActionBar(binding.toolbar)

        // Инициализация NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

        binding.bottomNavigationView.setupWithNavController(navController)

        // Отключаем обновление фрагмента при выборе той же вкладки
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {  }
    }

    // Функция для вызова из фрагментов
    fun setNavigationBarVisible(isVisible: Boolean) {
        if (isVisible) {
            binding.bottomNavigationView.visible()
        } else {
            binding.bottomNavigationView.gone()
        }
    }

    fun setToolbarTitle(title: String) {
        binding.tvToolbar.text = title
    }

}