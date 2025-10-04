package com.plaugig.todo2.ui.fragments.main

/**
 * Created by George on 9/14/25.
 */
interface MainEventListener {
	fun onOpenTask(id: Int)
	fun onDeleteTask(id: Int)
	fun onTaskDone(id: Int, isDone: Boolean)
}