package com.raqun.android.ui.logout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqun.android.data.source.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LogoutViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val logoutLiveData = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            try {
                userRepository.logout()
            } catch (_: Exception) { }
            userRepository.dropSession()
            logoutLiveData.postValue(true)
        }
    }

    fun getLogoutLiveData() = logoutLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()
}
