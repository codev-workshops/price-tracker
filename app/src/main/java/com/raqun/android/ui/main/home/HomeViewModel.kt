package com.raqun.android.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.WebApp
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val topFollowedProducts = MutableLiveData<DataBean<List<Product>>>()
    private val recentFollowedProducts = MutableLiveData<DataBean<List<Product>>>()
    private val discountedProducts = MutableLiveData<DataBean<List<Product>>>()
    private val topWebApps = MutableLiveData<DataBean<List<WebApp>>>()

    init {
        fetchData()
    }

    fun getTopFollowedProducts() = topFollowedProducts
    fun getRecentFollowedProducts() = recentFollowedProducts
    fun getDiscountedProducts() = discountedProducts
    fun getTopWebApps() = topWebApps

    private fun fetchData() {
        val page = Page(0, 15)
        topFollowedProducts.value = UiDataBean.fetching(null)
        recentFollowedProducts.value = UiDataBean.fetching(null)
        discountedProducts.value = UiDataBean.fetching(null)
        topWebApps.value = UiDataBean.fetching(null)

        viewModelScope.launch {
            try {
                val result = productRepository.getTopFollowedProducts(page)
                topFollowedProducts.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                topFollowedProducts.value = UiDataBean.error(null, e.getError())
            }
        }

        viewModelScope.launch {
            try {
                val result = productRepository.getRecentFollowedProducts(page)
                recentFollowedProducts.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                recentFollowedProducts.value = UiDataBean.error(null, e.getError())
            }
        }

        viewModelScope.launch {
            try {
                val result = productRepository.getDiscountedProducts(page)
                discountedProducts.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                discountedProducts.value = UiDataBean.error(null, e.getError())
            }
        }

        viewModelScope.launch {
            try {
                val result = productRepository.getTopWebApps(page)
                topWebApps.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                topWebApps.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
