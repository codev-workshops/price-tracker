package com.raqun.android.data.source

import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm

interface ProductDataSource {
    suspend fun getRecentFollowedProducts(page: Page): PagedResponse<Product>
    suspend fun getTopFollowedProducts(page: Page): PagedResponse<Product>
    suspend fun getDiscountedProducts(page: Page): PagedResponse<Product>
    suspend fun getTopWebApps(page: Page): PagedResponse<WebApp>
    suspend fun addProduct(addProductRequest: AddProductRequest)
    suspend fun getAlarms(alarmRequest: AlarmRequest): PagedResponse<Alarm>
}
