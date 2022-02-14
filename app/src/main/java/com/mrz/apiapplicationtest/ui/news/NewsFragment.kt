package com.mrz.apiapplicationtest.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.databinding.FragmentNewsBinding
import com.mrz.apiapplicationtest.ui.core.BaseFragment
import com.mrz.apiapplicationtest.viewmodel.news.NewsViewModel

class NewsFragment:
    BaseFragment<NewsViewModel, FragmentNewsBinding>() {

    override val viewModel: NewsViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsBinding =
        FragmentNewsBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setTitle(getString(R.string.title_news))
    }
}