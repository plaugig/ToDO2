package com.plaugig.todo2.ui.fragments.main

/**
 * Created by George on 10/19/25.
 */
sealed interface MainFragmentUiAction {
	data class OpenTask(
		val id: Int
	) : MainFragmentUiAction
}