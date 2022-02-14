package com.mrz.apiapplicationtest.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.viewbinding.ViewBinding
import com.mrz.apiapplicationtest.ui.MainActivity
import com.mrz.apiapplicationtest.viewmodel.core.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel, VB : ViewBinding>
    : Fragment() {

    // Переменная для вью модели
    protected abstract val viewModel: T

    // Внутренняя переменная для биндинга
    private var _binding: VB? = null
    val binding get() = _binding!!

    // Инициализация биндинга
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    // Переменная MainActivity для использования во фрагментах
    protected val mainActivity: MainActivity get() = requireActivity() as MainActivity

     /**
     * Переменная для указания видимости навигации. При значении false делает навигацию невидимой.
     */
    protected var isNavigationEnabled: Boolean = true

    private var _title: String = "Главная"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    abstract fun setupViews()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        setupNavigationBarVisibility()
        mainActivity.setToolbarTitle(_title)
    }

    // Вызов только в onStart
    private fun setupNavigationBarVisibility() {
        mainActivity.setNavigationBarVisible(isNavigationEnabled)
    }

    protected fun setTitle(title: String) {
        _title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}