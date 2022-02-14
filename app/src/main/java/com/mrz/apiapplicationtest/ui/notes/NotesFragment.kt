package com.mrz.apiapplicationtest.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.FragmentNotesBinding
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.viewmodel.notes.NotesViewModel

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
    }
}