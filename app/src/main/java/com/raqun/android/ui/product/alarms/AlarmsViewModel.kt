package com.raqun.android.ui.product.alarms

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Alarm
import com.raqun.android.model.UiDataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AlarmsViewModel @Inject constructor(private val productRepository: ProductRepository,
                                          private val userRepository: UserRepository)
    : ViewModel() {

    private val productIdLiveData = MutableLiveData<String>()

    private val alarmsLiveData = MediatorLiveData<DataBean<List<Alarm>>>()

    init {
        alarmsLiveData.addSource(productIdLiveData) { productId: String? ->
            if (isUserLoggedIn() && productId != null) {
                getAlarmsOfProduct(productId)
            }
        }
    }

    fun getAlarms() = alarmsLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun setProductId(productId: String?) {
        if (productId == null) return
        if (productId == productIdLiveData.value) return
        productIdLiveData.value = productId
    }

    private fun getAlarmsOfProduct(productId: String) {
        alarmsLiveData.value = UiDataBean.fetching(null)
        viewModelScope.launch {
            try {
                val result = productRepository.getAlarms(AlarmRequest(productId))
                alarmsLiveData.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                alarmsLiveData.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
