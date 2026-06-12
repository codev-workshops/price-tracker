package com.raqun.android.data.source.remote

import com.raqun.android.api.RaqunServices
import com.raqun.android.data.source.ProductDataSource
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val raqunServices: RaqunServices)
    : ProductDataSource {

    override suspend fun getTopFollowedProducts(page: Page): PagedResponse<Product> =
            raqunServices.getTopFollowedProducts(page).data

    override suspend fun getDiscountedProducts(page: Page): PagedResponse<Product> =
            raqunServices.getDiscountedProducts(page).data

    override suspend fun getRecentFollowedProducts(page: Page): PagedResponse<Product> =
            raqunServices.getRecentFollowedProducts(page).data

    override suspend fun getTopWebApps(page: Page): PagedResponse<WebApp> =
            raqunServices.getTopWebApps(page).data

    override suspend fun addProduct(addProductRequest: AddProductRequest) =
            raqunServices.addProduct(addProductRequest)

    override suspend fun getAlarms(alarmRequest: AlarmRequest): PagedResponse<Alarm> =
            raqunServices.getAlarms(alarmRequest).data
}
