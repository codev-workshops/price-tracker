package com.raqun.android.ui.main.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val products = MutableLiveData<DataBean<List<Product>>>()

    init {
        getFavoriteProducts()
    }

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun getProducts() = products

    private fun getFavoriteProducts() {
        if (!isUserLoggedIn()) return

        products.value = UiDataBean.fetching(null)
        viewModelScope.launch {
            try {
                val result = userRepository.getFavoriteProducts(Page(0, 15))
                products.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                products.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
