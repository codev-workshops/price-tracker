package com.raqun.android.ui.main.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.UserRepository
import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.UiDataBean
import com.raqun.android.extensions.getError
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val notificationsLiveData = MutableLiveData<DataBean<List<Notification>>>()

    init {
        fetchNotifications()
    }

    fun getNotifications() = notificationsLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    private fun fetchNotifications() {
        if (!isUserLoggedIn()) return

        notificationsLiveData.value = UiDataBean.fetching(null)
        viewModelScope.launch {
            try {
                val result = userRepository.getNotifications(Page())
                notificationsLiveData.value = UiDataBean.success(result.items)
            } catch (e: Exception) {
                notificationsLiveData.value = UiDataBean.error(null, e.getError())
            }
        }
    }
}
