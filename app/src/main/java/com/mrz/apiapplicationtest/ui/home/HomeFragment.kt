package com.mrz.apiapplicationtest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mrz.apiapplicationtest.databinding.FragmentHomeBinding
import com.mrz.apiapplicationtest.extensions.gone
import com.mrz.apiapplicationtest.extensions.visible
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.viewmodel.core.Status
import com.mrz.apiapplicationtest.viewmodel.home.HomeViewModel
import kotlinx.coroutines.flow.collect

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupViews() {
        viewModel.getNote1()
        subscribeOnNoteRequest()
    }

    private fun subscribeOnNoteRequest() {
        lifecycleScope.launchWhenStarted {
            viewModel.note.collect { note ->
                when (note.status) {
                    Status.SUCCESS -> {
                        with(binding) {
                            progressBar.gone()
                            tvId.text = note.data?.id.toString()
                            tvTitle.text = note.data?.title
                            tvDescription.text = note.data?.description
                        }
                    }
                    Status.ERROR -> {
                        binding.tvId.text = "Ошибка"
                        binding.progressBar.gone()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visible()
                    }
                }
            }
        }
    }
}