package com.raqun.android.api

import com.raqun.android.model.*
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.request.RegisterTokenRequest
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.DefaultResponse
import com.raqun.android.api.response.PagedResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface RaqunServices {

    @FormUrlEncoded
    @POST("token")
    suspend fun auth(@Field("username") username: String,
                     @Field("password") password: String,
                     @Field("grant_type") grantType: String): User

    @POST("Register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest)

    @POST("Logout")
    suspend fun logout()

    @POST("Notification")
    suspend fun getNotifications(@Body page: Page): DefaultResponse<PagedResponse<Notification>>

    @POST("SaveDevice")
    suspend fun registerToken(@Body registerTokenRequest: RegisterTokenRequest)

    @POST("Featured/RecentFollowedProducts")
    suspend fun getRecentFollowedProducts(@Body page: Page): DefaultResponse<PagedResponse<Product>>

    @POST("Featured/TopFollowedProducts")
    suspend fun getTopFollowedProducts(@Body page: Page): DefaultResponse<PagedResponse<Product>>

    @POST("Featured/DiscountedProducts")
    suspend fun getDiscountedProducts(@Body page: Page): DefaultResponse<PagedResponse<Product>>

    @POST("WebSite")
    suspend fun getTopWebApps(@Body page: Page): DefaultResponse<PagedResponse<WebApp>>

    @POST("UserProduct")
    suspend fun getFavoriteProducts(@Body page: Page): DefaultResponse<PagedResponse<Product>>

    @POST("UserProduct/Add")
    suspend fun addProduct(@Body addProductRequest: AddProductRequest)

    @POST("Alarm")
    suspend fun getAlarms(@Body alarmRequest: AlarmRequest): DefaultResponse<PagedResponse<Alarm>>
}
