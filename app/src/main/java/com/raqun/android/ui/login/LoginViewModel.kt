package com.raqun.android.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val loginLiveData = MutableLiveData<DataBean<User>>()

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun login(userName: String, password: String) {
        loginLiveData.postValue(UiDataBean.fetching(null))
        viewModelScope.launch {
            try {
                val user = userRepository.login(userName, password)
                loginLiveData.value = UiDataBean.success(user)
            } catch (e: Exception) {
                loginLiveData.value = UiDataBean.error(null, e.getError())
            }
        }
    }

    fun getLoginLiveData() = loginLiveData
}
