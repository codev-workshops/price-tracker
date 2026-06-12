package com.raqun.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

/**
 * Created by tyln on 27/07/2017.
 */
abstract class BinderFragment<VB : ViewDataBinding, VM : ViewModel> : BaseFragment() {

    @Inject protected lateinit var vmFactory: ViewModelProvider.Factory
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    abstract fun getModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(getModelClass())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        initView()
        return binding.root
    }

    protected open fun initView() {
        // Can be overridden from subclasses
    }
}
