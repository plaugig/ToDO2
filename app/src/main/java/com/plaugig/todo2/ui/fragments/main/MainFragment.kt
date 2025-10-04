package com.plaugig.todo2.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import com.plaugig.todo2.databinding.FragmentMainBinding
import com.plaugig.todo2.ui.days.DaysAdapter
import com.plaugig.todo2.ui.days.DaysItemDecoration
import com.plaugig.todo2.ui.task.TaskAdapter
import com.plaugig.todo2.ui.task.TaskItemDecoration
import com.plaugig.todo2.ui.task.swipe.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

	private val viewModel: MainViewModel by viewModels()

	private var _binding: FragmentMainBinding? = null
	private val binding get() = _binding!!


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentMainBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val dayAdapter = DaysAdapter()
		val taskAdapter = TaskAdapter(
			listener = viewModel
		)

		binding.days.adapter = dayAdapter
		binding.tasks.adapter = taskAdapter

		binding.days.addItemDecoration(DaysItemDecoration(requireContext()))
		binding.tasks.addItemDecoration(TaskItemDecoration(requireContext()))

		val itemTouchHelper = ItemTouchHelper(
			SwipeToDeleteCallback(requireContext())
		)
		itemTouchHelper.attachToRecyclerView(binding.tasks)


		binding.addTask.setOnClickListener {
			viewModel.addTask()
		}


		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.state.collect { state ->
					taskAdapter.task = state
				}
			}
		}

		viewModel.days.observe(viewLifecycleOwner) { days ->
			dayAdapter.days = days
		}

		ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}