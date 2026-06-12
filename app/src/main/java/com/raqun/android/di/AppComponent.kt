package com.raqun.android.di

import com.raqun.android.RaqunApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by tyln on 26/07/2017.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ApiModule::class,
    ActivityModule::class,
    ServiceModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RaqunApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: RaqunApp)
}
