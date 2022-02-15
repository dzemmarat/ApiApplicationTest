package com.mrz.apiapplicationtest.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.FragmentMessagesBinding
import com.mrz.apiapplicationtest.extensions.gone
import com.mrz.apiapplicationtest.extensions.visible
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.ui.messages.adapter.NotesAdapter
import com.mrz.apiapplicationtest.viewmodel.core.Status
import com.mrz.apiapplicationtest.viewmodel.messages.MessagesViewModel
import kotlinx.coroutines.flow.collect

class MessagesFragment :
    BaseFragment<MessagesViewModel, FragmentMessagesBinding>() {

    override val viewModel: MessagesViewModel by viewModels()
    private var adapter = NotesAdapter(mutableListOf())

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMessagesBinding =
        FragmentMessagesBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setTitle(getString(R.string.title_messages))
        viewModel.getNotes()
        setupRecyclerView()
        subscribeOnNoteRequest()
    }

    private fun subscribeOnNoteRequest() {
        lifecycleScope.launchWhenStarted {
            viewModel.notes.collect { notes ->
                when (notes.status) {
                    Status.SUCCESS -> {
                        with(binding) {
                            progressBar.gone()
                            notes.data?.let {
                                recyclerView.adapter = NotesAdapter(it)
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.gone()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visible()
                    }
                    Status.IDLE -> {}
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }


}