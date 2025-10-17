package com.plaugig.todo2.ui.bottom.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.plaugig.todo2.databinding.CreateTaskBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTaskBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: CreateTaskBottomSheetViewModel by viewModels()

    private var _binding: CreateTaskBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateTaskBottomSheetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.titleText.addTextChangedListener { text ->
            viewModel.setTitle(text.toString())
        }

        binding.descriptionText.addTextChangedListener { text ->
            viewModel.setDescription(text.toString())
        }

        binding.save.setOnClickListener {
            viewModel.save()
            dismiss()
        }
    }



}