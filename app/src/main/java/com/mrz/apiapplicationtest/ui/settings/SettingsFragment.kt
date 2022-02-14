package com.mrz.apiapplicationtest.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.FragmentSettingsBinding
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.viewmodel.messages.MessagesViewModel
import com.mrz.apiapplicationtest.viewmodel.settings.SettingsViewModel

class SettingsFragment:
    BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {

    override val viewModel: SettingsViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding =
        FragmentSettingsBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setTitle(getString(R.string.title_settings))
    }
}