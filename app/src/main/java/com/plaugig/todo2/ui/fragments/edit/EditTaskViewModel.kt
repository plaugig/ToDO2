package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.ViewModel
import com.plaugig.todo2.domain.edit.EditInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
@HiltViewModel
class EditTaskViewModel @Inject constructor(
	private val interactor: EditInteractor
) : ViewModel() {


	val options = interactor.getOptions()
}