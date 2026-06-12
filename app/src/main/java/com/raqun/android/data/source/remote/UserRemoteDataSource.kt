package com.raqun.android.data.source.remote

import com.raqun.android.api.ApiConstants
import com.raqun.android.api.RaqunServices
import com.raqun.android.data.source.UserDataSource
import com.raqun.android.model.*
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.PagedResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val raqunServices: RaqunServices) : UserDataSource {

    override fun getUserCredentials(): User? = null

    override suspend fun saveUser(registerRequest: RegisterRequest) =
            raqunServices.registerUser(registerRequest)

    override suspend fun login(userName: String, password: String): User =
            raqunServices.auth(userName, password, ApiConstants.DEFAULT_GRANT_TYPE)

    override suspend fun logout() = raqunServices.logout()

    override fun contact(messageType: Int, message: String) {
        // No-op: analytics removed
    }

    override suspend fun getNotifications(page: Page): PagedResponse<Notification> =
            raqunServices.getNotifications(page).data

    override suspend fun getFavoriteProducts(page: Page): PagedResponse<Product> =
            raqunServices.getFavoriteProducts(page).data
}
