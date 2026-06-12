package com.raqun.android.data.source

import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.User
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.PagedResponse

interface UserDataSource {
    fun getUserCredentials(): User?
    suspend fun saveUser(registerRequest: RegisterRequest)
    suspend fun login(userName: String, password: String): User
    suspend fun logout()
    fun contact(messageType: Int, message: String)
    suspend fun getNotifications(page: Page): PagedResponse<Notification>
    suspend fun getFavoriteProducts(page: Page): PagedResponse<Product>
}
