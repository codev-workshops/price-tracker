package com.raqun.android.data.source

import com.raqun.android.RaqunApp
import com.raqun.android.session.State
import com.raqun.android.data.source.local.UserLocalDataSource
import com.raqun.android.data.source.remote.UserRemoteDataSource
import com.raqun.android.model.*
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.PagedResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource,
                                         private val userRemoteDataSource: UserRemoteDataSource) : UserDataSource {

    override fun getUserCredentials(): User? = userLocalDataSource.getUserCredentials()

    override suspend fun saveUser(registerRequest: RegisterRequest) =
            userRemoteDataSource.saveUser(registerRequest)

    override suspend fun login(userName: String, password: String): User {
        val user = userRemoteDataSource.login(userName, password)
        RaqunApp.sessionManager.updateSession(user)
        userLocalDataSource.saveCredantials(user)
        return user
    }

    override suspend fun logout() = userRemoteDataSource.logout()

    override fun contact(messageType: Int, message: String) {
        userRemoteDataSource.contact(messageType, message)
    }

    override suspend fun getNotifications(page: Page): PagedResponse<Notification> {
        return userRemoteDataSource.getNotifications(page)
    }

    override suspend fun getFavoriteProducts(page: Page): PagedResponse<Product> {
        return userRemoteDataSource.getFavoriteProducts(page)
    }

    fun isUserLoggedIn() = RaqunApp.sessionManager.getState() == State.LOGIN

    fun getCurrentUser() = RaqunApp.sessionManager.getCurrentUser()

    fun dropSession() {
        userLocalDataSource.clearCredentials()
        RaqunApp.sessionManager.dropSession()
    }
}
