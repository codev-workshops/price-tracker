package com.raqun.android.ui.products

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.ProductListType
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.data.DataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private var productType: ProductListType? = null
    private val products = MediatorLiveData<DataBean<List<Product>>>()

    fun getProducts() = products

    fun setProductType(productListType: ProductListType) {
        if (productType != null) return
        this.productType = productListType

        products.value = UiDataBean.fetching(null)
        viewModelScope.launch {
            try {
                val page = Page(0, 15)
                val result: PagedResponse<Product> = when (productListType) {
                    ProductListType.TOP -> productRepository.getTopFollowedProducts(page)
                    ProductListType.RECENT -> productRepository.getRecentFollowedProducts(page)
                    ProductListType.DISCOUNT -> productRepository.getDiscountedProducts(page)
                    ProductListType.FAV -> productRepository.getRecentFollowedProducts(page)
                }
                products.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                products.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
