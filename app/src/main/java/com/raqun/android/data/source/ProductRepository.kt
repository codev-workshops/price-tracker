package com.raqun.android.data.source

import com.raqun.android.data.source.remote.ProductRemoteDataSource
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource)
    : ProductDataSource {

    override suspend fun getTopFollowedProducts(page: Page): PagedResponse<Product> =
            productRemoteDataSource.getTopFollowedProducts(page)

    override suspend fun getDiscountedProducts(page: Page): PagedResponse<Product> =
            productRemoteDataSource.getDiscountedProducts(page)

    override suspend fun getRecentFollowedProducts(page: Page): PagedResponse<Product> =
            productRemoteDataSource.getRecentFollowedProducts(page)

    override suspend fun getTopWebApps(page: Page): PagedResponse<WebApp> =
            productRemoteDataSource.getTopWebApps(page)

    override suspend fun addProduct(addProductRequest: AddProductRequest) =
            productRemoteDataSource.addProduct(addProductRequest)

    override suspend fun getAlarms(alarmRequest: AlarmRequest): PagedResponse<Alarm> =
            productRemoteDataSource.getAlarms(alarmRequest)
}
