package com.raqun.android

import android.app.Activity
import android.app.Application
import android.app.Service
import com.raqun.android.data.source.local.UserHelper
import com.raqun.android.di.DaggerAppComponent
import com.raqun.android.session.SessionManager
import com.raqun.android.session.SessionProvider
import com.raqun.android.util.SharedPrefHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.*
import javax.inject.Inject

/**
 * Created by tyln on 22/07/2017.
 */
class RaqunApp : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        RaqunApp.sessionManager = SessionManager(getOrCreateSessionKey()).also {
            it.updateSession(UserHelper.getUserCredentials(this))
        }

        with(DaggerAppComponent.builder()) {
            application(this@RaqunApp)
            build()
        }.also {
            it.inject(this)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun getOrCreateSessionKey(): String {
        var session: String? = SharedPrefHelper.getSession(this, null)
        if (session == null) {
            session = UUID.randomUUID().toString()
            SharedPrefHelper.putSession(this, session)
        }
        return session
    }

    companion object {
        lateinit var sessionManager: SessionProvider
            private set
    }
}
