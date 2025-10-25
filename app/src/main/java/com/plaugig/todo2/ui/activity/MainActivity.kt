package com.plaugig.todo2.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.plaugig.todo2.R
import com.plaugig.todo2.databinding.ActivityMainBinding
import com.plaugig.todo2.ui.fragments.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        showMainFragment()


    }


    private fun showMainFragment() {
        var fragment = supportFragmentManager.findFragmentById(binding.content.id)

        if (fragment == null) {
            fragment = MainFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(binding.content.id, fragment)
                .commit()
        }
    }
}