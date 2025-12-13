package com.plaugig.todo2.ui.fragments.edit

import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

/**
 * Created by George on 12/13/25.
 */
interface EditTaskListener {
	fun onPrioritySelected(priority: OptionsPriorityType)
}