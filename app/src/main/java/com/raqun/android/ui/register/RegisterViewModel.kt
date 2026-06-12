package com.raqun.android.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.data.DataBean
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val registerLiveData = MutableLiveData<DataBean<Boolean>>()

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun getRegisterLiveData() = registerLiveData

    fun register(email: String, fullName: String, contactPermission: Boolean,
                 userName: String, password: String) {
        val registerRequest = RegisterRequest(email, fullName, contactPermission, userName, password)
        registerLiveData.postValue(UiDataBean.fetching(null))
        viewModelScope.launch {
            try {
                userRepository.saveUser(registerRequest)
                registerLiveData.value = UiDataBean.success(true)
            } catch (e: Exception) {
                registerLiveData.value = UiDataBean.error(false, e.getError())
            }
        }
    }
}
