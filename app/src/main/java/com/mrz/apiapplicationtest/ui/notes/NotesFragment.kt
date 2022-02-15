package com.mrz.apiapplicationtest.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.FragmentNotesBinding
import com.mrz.apiapplicationtest.extensions.gone
import com.mrz.apiapplicationtest.extensions.visible
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.viewmodel.core.Status
import com.mrz.apiapplicationtest.viewmodel.notes.NotesViewModel
import kotlinx.coroutines.flow.collect

class NotesFragment:
    BaseFragment<NotesViewModel, FragmentNotesBinding>() {

    override val viewModel: NotesViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotesBinding =
        FragmentNotesBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setTitle(getString(R.string.title_notes))
        binding.btnSearch.setOnClickListener {
            viewModel.getNoteById(binding.etId.text.toString().toInt())
        }
        subscribeOnNoteRequest()
    }

    private fun subscribeOnNoteRequest() {
        lifecycleScope.launchWhenStarted {
            viewModel.note.collect { note ->
                when (note.status) {
                    Status.SUCCESS -> {
                        with(binding) {
                            progressBar.gone()
                            tvId.visible()
                            tvTitle.visible()
                            tvDescription.visible()

                            tvId.text = note.data?.id.toString()
                            tvTitle.text = note.data?.userId
                            tvDescription.text = note.data?.title
                        }
                    }
                    Status.ERROR -> {
                        binding.tvId.text = "Ошибка"
                        binding.tvTitle.gone()
                        binding.tvDescription.gone()
                        binding.progressBar.gone()
                    }
                    Status.LOADING -> {
                        binding.tvId.gone()
                        binding.tvTitle.gone()
                        binding.tvDescription.gone()
                        binding.progressBar.visible()
                    }
                    Status.IDLE -> {

                    }
                }
            }
        }
    }
}