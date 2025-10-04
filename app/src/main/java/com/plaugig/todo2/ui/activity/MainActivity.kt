package com.plaugig.todo2.ui.activity

import android.R
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.plaugig.todo2.ui.fragments.main.MainFragment

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		showMainFragment()
	}


	private fun showMainFragment() {
		var fragment = supportFragmentManager.findFragmentById(R.id.content)

		if (fragment == null) {
			fragment = MainFragment()
			supportFragmentManager
				.beginTransaction()
				.replace(R.id.content, fragment)
				.commit()
		}
	}
}