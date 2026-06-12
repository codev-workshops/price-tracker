package com.raqun.android.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.data.DataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AddProductViewModel @Inject constructor(private val productRepository: ProductRepository,
                                              private val userRepository: UserRepository)
    : ViewModel() {

    private val addProduct = MutableLiveData<DataBean<Boolean>>()

    fun getAddProduct() = addProduct

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun addProduct(url: String) {
        addProduct.value = UiDataBean.fetching(null)
        viewModelScope.launch {
            try {
                productRepository.addProduct(AddProductRequest(url))
                addProduct.value = UiDataBean.success(true)
            } catch (e: Exception) {
                addProduct.value = UiDataBean.error(false, e.getError())
            }
        }
    }
}
