package com.raqun.android.data.source.local

import android.content.Context
import com.raqun.android.data.source.UserDataSource
import com.raqun.android.model.User
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.api.response.PagedResponse
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val context: Context) : UserDataSource {

    override fun getUserCredentials(): User? = UserHelper.getUserCredentials(context)

    override suspend fun saveUser(registerRequest: RegisterRequest) { }

    override suspend fun login(userName: String, password: String): User {
        throw UnsupportedOperationException("Local login not supported")
    }

    override suspend fun logout() {
        UserHelper.clearCredentials(context)
    }

    override fun contact(messageType: Int, message: String) { }

    override suspend fun getNotifications(page: Page): PagedResponse<Notification> {
        throw UnsupportedOperationException("Local notifications not supported")
    }

    override suspend fun getFavoriteProducts(page: Page): PagedResponse<Product> {
        throw UnsupportedOperationException("Local favorites not supported")
    }

    fun saveCredantials(user: User?) {
        UserHelper.saveUserCredentials(context, user)
    }

    fun clearCredentials() {
        UserHelper.clearCredentials(context)
    }
}
