package com.raqun.android.di

import com.raqun.android.BuildConfig
import com.raqun.android.api.ApiConstants
import com.raqun.android.api.DefaultRequestInterceptor
import com.raqun.android.api.RaqunServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides @Singleton fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides @Singleton fun provideRequestInterceptor(): DefaultRequestInterceptor
            = DefaultRequestInterceptor()

    @Provides @Singleton fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides @Singleton fun provideOkHttpClient(requestInterceptor: DefaultRequestInterceptor,
                                                 loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(requestInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            connectTimeout(ApiConstants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Provides @Singleton fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides @Singleton fun provideApiServices(retrofit: Retrofit): RaqunServices
            = retrofit.create(RaqunServices::class.java)
}
