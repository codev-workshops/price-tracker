package com.raqun.android.ui.main.home

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqun.android.R
import com.raqun.android.databinding.FragmentHomeBinding
import com.raqun.android.extensions.observeApi
import com.raqun.android.model.ProductListType
import com.raqun.android.ui.BinderFragment
import com.raqun.android.extensions.setup
import com.raqun.android.ui.main.FabProvider

/**
 * Created by tyln on 29/07/2017.
 */
@AndroidEntryPoint
class HomeFragment : BinderFragment<FragmentHomeBinding, HomeViewModel>(), HomeView {

    override fun getModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is FabProvider) {
            (activity as FabProvider).showFab()
        }

        viewModel.getTopFollowedProducts().observeApi(this, { bean -> binding.topProductsBean = bean })

        viewModel.getRecentFollowedProducts().observeApi(this, { bean -> binding.recentProductsBean = bean })

        viewModel.getDiscountedProducts().observeApi(this, { bean -> binding.discountedProductsBean = bean })

        viewModel.getTopWebApps().observeApi(this, { bean -> binding.topFollowedAppsBean = bean })
    }

    override fun initView() {
        binding.homeView = this
        binding.navigator = navigationController
        binding.recyclerviewTop?.setup(requireActivity(), LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewRecent?.setup(requireActivity(), LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewDiscount?.setup(requireActivity(), LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewApps?.setup(requireActivity(), LinearLayoutManager.HORIZONTAL)
    }

    override fun onMoreTopClicked() {
        navigationController?.navigateToProducts(ProductListType.TOP)
    }

    override fun onMoreRecentClicked() {
        navigationController?.navigateToProducts(ProductListType.RECENT)
    }

    override fun onMoreDiscountClicked() {
        navigationController?.navigateToProducts(ProductListType.DISCOUNT)
    }

    override fun onMoreAppsClicked() {
        //navigationController?.navigateToProducts()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}