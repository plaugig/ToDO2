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
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private var _binding: FragmentRedactTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EditTaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedactTaskBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = EditTaskAdapter(viewModel)

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
            viewModel.titleState.collectLatest { title ->
               if (binding.titleText.text.toString() != title){
                   binding.titleText.setText(title)
               }
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.descriptionState.collectLatest { description ->
                if (binding.descriptionText.text.toString() != description){
                    binding.descriptionText.setText(description)
                }
            }

        }

        binding.deleteButton.setOnClickListener {

            viewModel.onDeleteTask()
            parentFragmentManager.popBackStack()
        }

        binding.titleText.addTextChangedListener{ text ->
            viewModel.setTitle(text.toString())
        }

        binding.descriptionText.addTextChangedListener { text ->
            viewModel.setDescription(text.toString())
        }

        binding.saveButton.setOnClickListener {
            viewModel.save()
            parentFragmentManager.popBackStack()
        }

    }
    companion object {
        const val TASK_ID = "task_id"

        fun newInstance(taskId: Int): EditTaskFragment {
            val fragment = EditTaskFragment()
            val bundle = Bundle()
            bundle.putInt(TASK_ID, taskId)
            fragment.arguments = bundle
            return fragment
        }
    }
}