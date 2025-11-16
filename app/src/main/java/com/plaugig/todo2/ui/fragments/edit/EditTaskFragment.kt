package com.plaugig.todo2.ui.fragments.edit

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
import com.plaugig.todo2.databinding.FragmentRedactTaskBinding
import com.plaugig.todo2.ui.fragments.edit.options.EditTaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private var _binding: FragmentRedactTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EditTaskViewModel>()

    companion object {
        private const val idTask = "taskId"

        fun newInstance(taskId: Int): EditTaskFragment {
            val fragment = EditTaskFragment()
            val bundle = Bundle()
            bundle.putInt(idTask, taskId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var taskId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskId = arguments?.getInt(idTask) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedactTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = EditTaskAdapter()

        binding.taskOptions.adapter = adapter
        binding.taskOptions.addItemDecoration(
            EditItemDecoration(requireContext())
        )

        binding.toolBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.options.collect { options ->
                        adapter.items = options
                    }
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewLifecycleOwner.lifecycleScope.launch {
                viewModel.taskDataState.collect { task ->
                    task?.let {
                        binding.titleText.setText(it.title)
                        binding.descriptionText.setText(it.description)
                    }
                }

        }

    }
}