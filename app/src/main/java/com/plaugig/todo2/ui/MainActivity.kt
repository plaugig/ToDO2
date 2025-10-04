package com.plaugig.todo2.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.plaugig.todo2.ui.fragment.MainFragment


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		showMainFragment()
	}


	private fun showMainFragment() {
		var fragment = supportFragmentManager.findFragmentById(android.R.id.content)

		if (fragment == null) {
			fragment = MainFragment()
			supportFragmentManager
				.beginTransaction()
				.replace(android.R.id.content, fragment)
				.commit()
		}
	}
}