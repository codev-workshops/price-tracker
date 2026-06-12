package com.raqun.android.di

import android.content.Context
import com.raqun.android.api.RaqunServices
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.data.source.ResourceRepository
import com.raqun.android.data.source.UserRepository
import com.raqun.android.data.source.local.ResourceLocalDataSource
import com.raqun.android.data.source.local.UserLocalDataSource
import com.raqun.android.data.source.remote.ProductRemoteDataSource
import com.raqun.android.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton fun provideProductRemoteDataSource(raqunServices: RaqunServices): ProductRemoteDataSource
            = ProductRemoteDataSource(raqunServices)

    @Provides @Singleton fun provideProductRepository(productRemoteDataSource: ProductRemoteDataSource): ProductRepository
            = ProductRepository(productRemoteDataSource)

    @Provides @Singleton fun provideUserLocalDataSource(@ApplicationContext context: Context)
            = UserLocalDataSource(context)

    @Provides @Singleton fun provideUserRemoteDataSource(raqunServices: RaqunServices)
            = UserRemoteDataSource(raqunServices)

    @Provides @Singleton fun provideUserRepository(userLocalDataSource: UserLocalDataSource,
                                                   userRemoteDataSource: UserRemoteDataSource)
            = UserRepository(userLocalDataSource, userRemoteDataSource)

    @Provides @Singleton fun provideResourceLocalDataSource(@ApplicationContext context: Context)
            = ResourceLocalDataSource(context)

    @Provides @Singleton fun provideResourceRepository(resourceLocalDataSource: ResourceLocalDataSource)
            = ResourceRepository(resourceLocalDataSource)
}
